package command;

import controller.ActionType;
import util.Pages;

import javax.servlet.http.HttpServletRequest;

public class NoCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, ActionType type) {

        String page = Pages.FORWARD_MAIN;

        if (type == ActionType.POST) {
            page = Pages.REDIRECT_MAIN;
        }
        return page;
    }
}
