package command;

import controller.ActionType;
import model.dao.connection.ConnectionPool;
import model.dao.validator.UserDataValidator;
import model.entity.User;
import model.entity.role.UserRole;
import model.service.UserService;
import model.service.exception.UserServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class.getName());


    @Override
    public String execute(HttpServletRequest request, ActionType type) {

        String page = Pages.REDIRECT_REGISTRATION;
        if (type == ActionType.POST) {
            HttpSession session = request.getSession();

            String login = request.getParameter(Parameters.LOGIN);
            String password = request.getParameter(Parameters.PASSWORD);
            String repeatPassword = request.getParameter(Parameters.REPEAT_PASSWORD);
            String firstName = request.getParameter(Parameters.FIRST_NAME);
            String lastName = request.getParameter(Parameters.LAST_NAME);
            String patronymic = request.getParameter(Parameters.PATRONYMIC);
            String userRole = request.getParameter(Parameters.USER_ROLE);

            List<String> errorMessages = UserDataValidator.validateRegistrationForm(login, password, repeatPassword, lastName, firstName, patronymic, userRole);

            UserService userService = (UserService) ServiceFactory.getService(ServiceType.USER_SERVICE);
            Connection connection = ConnectionPool.getInstance().getConnection();

            try {
                boolean uniquenessCheck = userService.checkLoginUniqueness(connection, login);
                if (errorMessages == null && uniquenessCheck) {

                    User newUser = new User(UserRole.valueOf(userRole.toUpperCase()), login, password.hashCode(), firstName, lastName, patronymic);
                    newUser.setUserId(userService.addUser(connection, newUser));

                    if (session.getAttribute(Parameters.USER) != null) {
                        session.invalidate();
                    }
                    session.setAttribute(Parameters.USER, newUser);
                    LOGGER.info("new user has been registered and log in as: " + newUser);
                    page = Pages.REDIRECT_PERSONAL_SETTINGS;
                } else {
                    String[] inputValues = {login, null, null, lastName, firstName, patronymic, userRole};
                    List<String> validValues = selectValidValues(inputValues, errorMessages);
                    LOGGER.debug(errorMessages);
                    session.setAttribute(Parameters.VALID_VALUES, validValues);
                    session.setAttribute(Parameters.ERRORS, errorMessages);
                    assert errorMessages != null;
                    if (errorMessages.get(0) == null && !uniquenessCheck) {
                        LOGGER.debug(Messages.LOGIN_NOT_UNIQUE);
                        session.setAttribute(Parameters.ERROR, Messages.LOGIN_NOT_UNIQUE);
                    }
                }
            } catch (UserServiceException e) {
                LOGGER.error(e.getMessage());
                request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
                page = Pages.REDIRECT_ERROR_PAGE;
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        } else {
            page = Pages.FORWARD_REGISTRATION;
        }
        return page;
    }

    private List<String> selectValidValues(String[] inputValues, List<String> errorMessages) {

        for (int i = 0; i < inputValues.length; i++) {
            if (inputValues[i] != null) {
                if (errorMessages.get(i) != null) {
                    inputValues[i] = null;
                }
            }
        }
        return Arrays.asList(inputValues);
    }
}

