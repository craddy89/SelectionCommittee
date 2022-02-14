package command;

import controller.ActionType;
import util.Pages;

import javax.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {
        return Pages.FORWARD_ERROR_PAGE;
    }
}
