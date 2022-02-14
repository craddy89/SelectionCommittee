package command;

import controller.ActionType;
import model.dao.validator.GradesValidator;
import model.entity.Applicant;
import model.entity.Enrollment;
import model.entity.Faculty;
import model.entity.User;
import model.entity.role.UserRole;
import model.entity.state.ApplicantState;
import model.entity.state.EnrollmentState;
import model.service.ApplicantService;
import model.service.FacultyService;
import model.service.exception.ApplicantServiceException;
import model.service.exception.FacultyServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;
import util.matcher.UrlMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ApplyCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ApplyCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType type) {

        String page = type == ActionType.POST ? Pages.REDIRECT_ERROR_PAGE : Pages.FORWARD_ERROR_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameters.USER);
        String facultyIdString = request.getParameter(Parameters.ID);
        Enrollment enrollment = (Enrollment) request.getAttribute(Parameters.ENROLLMENT);

        if (enrollment != null && enrollment.getState() == EnrollmentState.OPENED) {
            if (facultyIdString != null && user != null && user.getRole() == UserRole.APPLICANT) {
                int facultyId = Integer.parseInt(facultyIdString);

                try {
                    if (type == ActionType.POST) {
                        String[] subjectGradesStrings = request.getParameterValues(Parameters.GRADE);
                        if (GradesValidator.validateGrades(subjectGradesStrings)) {
                            Applicant newApplicant = new Applicant();
                            newApplicant.setUserId(user.getUserId());
                            newApplicant.setFacultyId(facultyId);
                            newApplicant.setEnrollmentId(enrollment.getEnrollmentId());
                            newApplicant.setApplicantState(ApplicantState.APPLIED);
                            ApplicantService applicantService = (ApplicantService) ServiceFactory.getService(ServiceType.APPLICANT_SERVICE);

                            if (applicantService.addApplicant(newApplicant, subjectGradesStrings)) {
                                LOGGER.info("applicant has been added: " + newApplicant);
                                page = Pages.REDIRECT_PERSONAL_SETTINGS;
                            } else {
                                LOGGER.warn("trying to apply while already applied: " + user);
                                session.setAttribute(Parameters.ERROR, Messages.ALREADY_APPLIED);
                                page = UrlMatcher.matchUrl(request.getHeader(Parameters.REFERER));
                            }
                        } else {
                            session.setAttribute(Parameters.ERROR, Messages.INVALID_GRADE);
                            page = UrlMatcher.matchUrl(request.getHeader(Parameters.REFERER));
                        }
                    } else {
                        FacultyService facultyService = (FacultyService) ServiceFactory.getService(ServiceType.FACULTY_SERVICE);
                        Faculty faculty = facultyService.getById(facultyId);
                        request.setAttribute(Parameters.FACULTY, faculty);
                        LOGGER.info("faculty set as attribute");
                        page = Pages.FORWARD_APPLY_PAGE;
                    }

                } catch (ApplicantServiceException | FacultyServiceException e) {
                    LOGGER.error(e.getMessage());
                    session.setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
                }
            } else {
                LOGGER.warn("wrong input parameters");
                session.setAttribute(Parameters.ERROR, Messages.NO_ACCESS);
            }
        } else {
            LOGGER.warn(Messages.WRONG_ENROLLMENT_STATE);
            session.setAttribute(Parameters.ERROR, Messages.WRONG_ENROLLMENT_STATE);
        }

        return page;
    }
}

