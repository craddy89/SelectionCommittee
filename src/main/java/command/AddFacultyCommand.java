package command;

import controller.ActionType;
import model.dao.validator.FacultyDataValidator;
import model.entity.Faculty;
import model.entity.Subject;
import model.service.FacultyService;
import model.service.SubjectService;
import model.service.exception.FacultyServiceException;
import model.service.exception.SubjectServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddFacultyCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddFacultyCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = actionType == ActionType.POST ? Pages.REDIRECT_ERROR_PAGE : Pages.FORWARD_ERROR_PAGE;
        HttpSession session = request.getSession();

        try {
            if (actionType == ActionType.GET) {
                SubjectService subjectService = (SubjectService) ServiceFactory.getService(ServiceType.SUBJECT_TYPE);
                List<Subject> subjects = subjectService.getAllSubjects();
                request.setAttribute(Parameters.SUBJECTS, subjects);
                LOGGER.info("subjects has been gotten and set as attribute");
                page = Pages.FORWARD_ADD_FACULTY;
            } else {
                String facultyName = request.getParameter(Parameters.FACULTY_NAME);
                String[] subjectsIdStrings = request.getParameterValues(Parameters.SUBJECT_ID);
                String capacityString = request.getParameter(Parameters.CAPACITY);
                if (FacultyDataValidator.validateFacultyName(facultyName) && subjectsIdStrings != null && FacultyDataValidator.validateCapacity(capacityString)) {
                    List<Subject> requiredSubjects = new ArrayList<>();
                    Subject subject;
                    for (String idString : subjectsIdStrings) {
                        subject = new Subject();
                        subject.setSubjectId(Integer.parseInt(idString));
                        requiredSubjects.add(subject);
                    }
                    Faculty faculty = new Faculty();
                    faculty.setName(facultyName);
                    faculty.setCapacity(Integer.parseInt(capacityString));
                    faculty.setRequiredSubjects(requiredSubjects);

                    FacultyService facultyService = (FacultyService) ServiceFactory.getService(ServiceType.FACULTY_SERVICE);
                    facultyService.addFaculty(faculty);

                    LOGGER.info("faculty has been added: " + faculty);
                    page = Pages.REDIRECT_VIEW_FACULTIES;
                } else {
                    page = Pages.REDIRECT_ADD_FACULTY;
                    session.setAttribute(Parameters.ERROR, Messages.INVALID_FACULTY_DATA);
                    LOGGER.warn(Messages.INVALID_FACULTY_DATA);
                }
            }
        } catch (FacultyServiceException | SubjectServiceException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            session.setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return page;
    }

}

