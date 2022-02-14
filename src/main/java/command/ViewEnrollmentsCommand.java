package command;

import controller.ActionType;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewEnrollmentsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewEnrollmentsCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        EnrollmentService enrollmentService = (EnrollmentService) ServiceFactory.getService(ServiceType.ENROLLMENT_SERVICE);

        try {
            List<Enrollment> enrollments = enrollmentService.getAllClosedEnrollments();
            if(!enrollments.isEmpty()){
                LOGGER.info("enrollments were found and set as attribute");
                request.setAttribute(Parameters.ENROLLMENTS, enrollments);
            }else{
                LOGGER.warn("enrollments weren't found");
            }
        } catch (EnrollmentServiceException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return Pages.FORWARD_VIEW_ENROLLMENTS;
    }
}
