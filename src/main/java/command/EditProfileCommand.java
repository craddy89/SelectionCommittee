package command;

import controller.ActionType;
import model.dao.validator.UserDataValidator;
import model.entity.User;
import model.service.UserService;
import model.service.exception.UserServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;
import util.matcher.UrlMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EditProfileCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(EditProfileCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {
        String page = UrlMatcher.matchUrl(request.getHeader(Parameters.REFERER));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameters.USER);
        if (actionType == ActionType.GET) {
            page = Pages.FORWARD_EDIT_PROFILE;
        } else {
            String password = request.getParameter(Parameters.PASSWORD);
            if (password != null && password.hashCode() == user.getPassword()) {

                String firstName = request.getParameter(Parameters.FIRST_NAME);
                String lastName = request.getParameter(Parameters.LAST_NAME);
                String patronymic = request.getParameter(Parameters.PATRONYMIC);
                List<String> errorMessages = UserDataValidator.validateEditingForm(lastName, firstName, patronymic);
                if (errorMessages == null) {
                    user.setLastName(lastName);
                    user.setFirstName(firstName);
                    user.setPatronymic(patronymic);

                    UserService userService = (UserService) ServiceFactory.getService(ServiceType.USER_SERVICE);
                    try {
                        userService.editUser(user);
                        LOGGER.info("user has been edit: "+user);
                        session.setAttribute(Parameters.USER,user);
                        page = Pages.REDIRECT_PERSONAL_SETTINGS;
                    } catch (UserServiceException e) {
                        LOGGER.error(e.getMessage());
                        request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
                        page = Pages.REDIRECT_ERROR_PAGE;
                    }
                } else {
                    session.setAttribute(Parameters.ERRORS, errorMessages);
                }
            } else {
                session.setAttribute(Parameters.ERROR, Messages.INCORRECT_PASSWORD);
            }
        }
        return page;
    }
}

