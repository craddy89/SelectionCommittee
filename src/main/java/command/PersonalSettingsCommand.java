package command;

import controller.ActionType;
import model.entity.Applicant;
import model.entity.User;
import model.entity.role.UserRole;
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
import java.util.Collections;
import java.util.List;

public class PersonalSettingsCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(PersonalSettingsCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.FORWARD_ERROR_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameters.USER);

        if (user != null) {
            page = Pages.FORWARD_PERSONAL_SETTINGS;
            if (user.getRole() == UserRole.APPLICANT) {
                ApplicantService applicantService= (ApplicantService) ServiceFactory.getService(ServiceType.APPLICANT_SERVICE);
                try {
                    List<Applicant> applicants = applicantService.getApplicantsByUserId(user.getUserId());
                    if(!applicants.isEmpty()) {
                        Collections.reverse(applicants);
                        request.setAttribute(Parameters.APPLICANTS , applicants);
                        LOGGER.info("applications were found and set as attribute");
                    }
                } catch (ApplicantServiceException e) {
                    LOGGER.error(e.getMessage());
                    session.setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
                }
            }
        } else {
            session.setAttribute(Parameters.ERROR, Messages.NO_ACCESS);
        }
        return page;
    }
}
