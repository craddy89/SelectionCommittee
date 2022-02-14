package filter.commandAccessFilter;

import model.entity.User;
import model.entity.role.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class CommandAccessFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(CommandAccessFilter.class.getName());
    private static final String DELIMITER = " ";

    private String contextPath;
    private List<String> userCommands;
    String exclusiveCommands;
    UserRole userRole;
    String logMessage;



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contextPath = filterConfig.getServletContext().getContextPath();
        String[] commands = filterConfig.getInitParameter(exclusiveCommands).split(DELIMITER);
        userCommands = Arrays.asList(commands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(Parameters.COMMAND);
        User user = (User) request.getSession().getAttribute(Parameters.USER);

        if ((user == null ||  user.getRole() != userRole) && userCommands.contains(command)) {
            LOGGER.debug(logMessage + command);
            request.getSession().setAttribute(Parameters.ERROR, Messages.NO_ACCESS);
            response.sendRedirect(contextPath + "/" + Pages.REDIRECT_ERROR_PAGE);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
