package command;

import controller.ActionType;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request, ActionType actionType);
}
