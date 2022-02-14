package filter;

import model.dao.connection.ConnectionPool;
import model.entity.Enrollment;
import model.service.EnrollmentService;
import model.service.exception.EnrollmentServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EnrollmentStateFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(EnrollmentStateFilter.class.getName());
    private EnrollmentService enrollmentService;
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contextPath = filterConfig.getServletContext().getContextPath();
        enrollmentService = (EnrollmentService) ServiceFactory.getService(ServiceType.ENROLLMENT_SERVICE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        try {
            Enrollment latestEnrollment = enrollmentService.getLatestEnrollment();
            if (latestEnrollment != null) {
                servletRequest.setAttribute(Parameters.ENROLLMENT, latestEnrollment);
                LOGGER.info("enrollment has been gotten and set as attribute");
            } else {
                LOGGER.warn("there was no one enrollment");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (EnrollmentServiceException | NullPointerException e) {
            LOGGER.error(e.getMessage());
            reInitConnectionPool();
            session.setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
            request.getRequestDispatcher(Pages.FORWARD_ERROR_PAGE).forward(request, response);
        }
    }

    private void reInitConnectionPool() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.closeAll();
        connectionPool.initPool();
        LOGGER.info("Connection pool has been reinitialized");
    }

    @Override
    public void destroy() {

    }
}

