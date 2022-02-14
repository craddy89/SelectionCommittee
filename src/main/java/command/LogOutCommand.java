package command;

import controller.ActionType;
import model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(LogOutCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType type) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameters.USER);

        if (user != null) {
            session.invalidate();
            LOGGER.info("user: " + user.getLogin()+" was log out");
        } else {
            LOGGER.debug("could not log out: user not identified");
        }
        return Pages.REDIRECT_MAIN;
    }
}