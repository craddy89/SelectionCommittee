package command;

import controller.ActionType;
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
import java.util.List;

public class ViewSubjectsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewSubjectsCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.FORWARD_ERROR_PAGE;

        SubjectService subjectService = (SubjectService) ServiceFactory.getService(ServiceType.SUBJECT_TYPE);

        try {
            List<Subject> subjects = subjectService.getAllSubjects();
            if (subjects != null) {
                LOGGER.info("subjects were found and set as attribute");
                request.setAttribute(Parameters.SUBJECTS, subjects);
            } else {
                LOGGER.warn("subjects weren't found");
            }
            page = Pages.FORWARD_VIEW_SUBJECTS;
        } catch (SubjectServiceException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return page;
    }
}