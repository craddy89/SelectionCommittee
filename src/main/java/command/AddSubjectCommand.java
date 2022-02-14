package command;

import controller.ActionType;
import model.dao.validator.FacultyDataValidator;
import model.entity.Subject;
import model.service.SubjectService;
import model.service.exception.SubjectServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;

public class AddSubjectCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AddSubjectCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.REDIRECT_VIEW_SUBJECTS;
        String subjectName = request.getParameter(Parameters.SUBJECT_NAME);


        SubjectService subjectService = (SubjectService) ServiceFactory.getService(ServiceType.SUBJECT_TYPE);
        if (FacultyDataValidator.validateSubjectName(subjectName)) {
            Subject subject = new Subject();
            subject.setName(subjectName);
            try {
                subjectService.insert(subject);
                LOGGER.info("subject was added: "+subject);
            } catch (SubjectServiceException e) {
                LOGGER.error(e.getMessage());
                page = Pages.REDIRECT_ERROR_PAGE;
                request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
            }
        }else{
            LOGGER.warn("invalid faculty data name");
            request.getSession().setAttribute(Parameters.ERROR,Messages.INVALID_SUBJECT_NAME);
        }
        return page;
    }
}
