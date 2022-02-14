package command;

import controller.ActionType;
import model.entity.User;
import model.service.UserService;
import model.service.exception.UserServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Parameters;
import util.matcher.UrlMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private static final Logger LOGGER= LogManager.getLogger(LoginCommand.class.getName());


    @Override
    public String execute(HttpServletRequest request, ActionType type) {

        String page = UrlMatcher.matchUrl(request.getHeader(Parameters.REFERER));

        HttpSession session = request.getSession();
        String login = request.getParameter(Parameters.LOGIN);
        String password = request.getParameter(Parameters.PASSWORD);

        LOGGER.debug("login: " + login + " password hashCode: " + password.hashCode());

        if (!password.equals("") && !login.equals("")) {
            UserService userService = (UserService) ServiceFactory.getService(ServiceType.USER_SERVICE);
            User user;
            try {
                user = userService.login(login);

                if (user != null) {
                    LOGGER.debug("found user: " + user);
                    long passwordHash = user.getPassword();

                    if (passwordHash == password.hashCode()) {
                        LOGGER.debug("successful login as "+user.getRole());
                        session.setAttribute(Parameters.USER, user);
                    } else {
                        LOGGER.info("incorrect password");
                        session.setAttribute(Parameters.LOGIN_ERROR, Messages.INCORRECT_PASSWORD);
                    }
                } else {
                    LOGGER.info("incorrect login");
                    session.setAttribute(Parameters.LOGIN_ERROR, Messages.INCORRECT_LOGIN);
                }
            } catch (UserServiceException e) {
                LOGGER.error(e.getMessage());
                session.setAttribute(Parameters.LOGIN_ERROR,Messages.INTERNAL_ERROR);
            }
        } else {
            LOGGER.info("field not filled");
            session.setAttribute(Parameters.LOGIN_ERROR, Messages.FIELDS_NOT_FILLED);
        }
        return page;
    }
}