package command;

import controller.ActionType;
import model.entity.Applicant;
import model.service.ApplicantService;
import model.service.exception.ApplicantServiceException;
import model.service.exception.SubjectServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;

public class ViewApplicationCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewApplicationCommand.class.getName());


    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.FORWARD_ERROR_PAGE;
        int applicantId = Integer.parseInt(request.getParameter(Parameters.ID));

        ApplicantService applicantService = (ApplicantService) ServiceFactory.getService(ServiceType.APPLICANT_SERVICE);

        try {
            Applicant applicant = applicantService.getApplicantById(applicantId);
            if (applicant != null) {
                request.setAttribute(Parameters.APPLICANT, applicant);
                request.setAttribute(Parameters.SUBJECTS, applicant.getSubjects());
                LOGGER.info("application has been found and set as attribute");

            } else {
                LOGGER.warn("application has not been found");
            }
            page = Pages.FORWARD_VIEW_APPLICATION;
        } catch (ApplicantServiceException | SubjectServiceException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return page;
    }
}