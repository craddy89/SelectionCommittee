package controller;

import command.Command;
import command.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Parameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp, ActionType.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp, ActionType.POST);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response, ActionType type) {

        String commandName = request.getParameter(Parameters.COMMAND);
        Command command = CommandManager.getCommand(commandName);
        LOGGER.info("Command retrieved: " + commandName);
        String page = command.execute(request, type);

        try {
            if (type == ActionType.GET) {
                LOGGER.info("Forwarded to: " + page);
                request.getRequestDispatcher(page).forward(request, response);
            } else {
                LOGGER.info("Redirected to: " + page);
                response.sendRedirect(request.getContextPath() + "/" + page);
            }
        } catch (ServletException | IOException e) {
            LOGGER.error("Exception at servlet: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
