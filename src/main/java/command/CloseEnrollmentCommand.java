package command;

import controller.ActionType;
import model.entity.Enrollment;
import model.entity.User;
import model.entity.role.UserRole;
import model.entity.state.EnrollmentState;
import model.service.EnrollmentService;
import model.service.exception.EnrollmentServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class CloseEnrollmentCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CloseEnrollmentCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.REDIRECT_ERROR_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameters.USER);
        Enrollment enrollment = (Enrollment) request.getAttribute(Parameters.ENROLLMENT);

        if (enrollment != null && enrollment.getState() == EnrollmentState.OPENED && user.getRole() == UserRole.ADMIN) {
            EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getService(ServiceType.ENROLLMENT_SERVICE);
            try {
                enrollmentService.closeCurrentEnrollment(new Timestamp(System.currentTimeMillis()));
                LOGGER.info("enrollment has been closed: " + enrollment);
                page = Pages.REDIRECT_MAIN;
            } catch (EnrollmentServiceException e) {
                LOGGER.error("could not close enrollment: " + e.getMessage());
                request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
            }
        } else {
            LOGGER.debug(Messages.NO_ACCESS);
            session.setAttribute(Parameters.ERROR, Messages.NO_ACCESS);
        }
        return page;
    }
}
