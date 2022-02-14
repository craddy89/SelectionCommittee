package command;

import controller.ActionType;
import model.entity.User;
import model.service.ApplicantService;
import model.service.exception.ApplicantServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelApplication implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CancelApplication.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.REDIRECT_ERROR_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameters.USER);
        String applicantIdString = request.getParameter(Parameters.APPLICANT_ID);
        int applicantId = Integer.parseInt(applicantIdString);

        ApplicantService applicantService = (ApplicantService) ServiceFactory.getService(ServiceType.APPLICANT_SERVICE);
        try {
            if (applicantService.cancelApplication(applicantId, user)) {
                LOGGER.info("application has been deleted by user: " + user);
                page = Pages.REDIRECT_PERSONAL_SETTINGS;
            } else {
                LOGGER.warn("user: " + user + "trying to delete not his application");
                page = Pages.REDIRECT_ERROR_PAGE;
                session.setAttribute(Parameters.ERROR, Messages.NO_ACCESS);
            }
        } catch (ApplicantServiceException e) {
            LOGGER.error(e.getMessage());
            session.setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return page;
    }
}