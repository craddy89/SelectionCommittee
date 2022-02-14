package command;

import controller.ActionType;
import model.entity.Enrollment;
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

public class OpenEnrollmentCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(OpenEnrollmentCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.REDIRECT_ERROR_PAGE;
        HttpSession session = request.getSession();

        Enrollment enrollment = (Enrollment) request.getAttribute(Parameters.ENROLLMENT);

        if (enrollment==null || enrollment.getState() == EnrollmentState.CLOSED) {

            EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getService(ServiceType.ENROLLMENT_SERVICE);
            try {
                enrollmentService.openNewEnrollment(new Timestamp(System.currentTimeMillis()));
                LOGGER.info("new enrollment was opened");
                page=Pages.REDIRECT_MAIN;
            } catch (EnrollmentServiceException e) {
                LOGGER.error(e.getMessage());
                session.setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
            }
        } else {
            LOGGER.debug(Messages.WRONG_ENROLLMENT_STATE);
            session.setAttribute(Parameters.ERROR, Messages.WRONG_ENROLLMENT_STATE);
        }
        return page;
    }
}
