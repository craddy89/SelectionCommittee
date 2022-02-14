package command;

import controller.ActionType;
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

public class DeleteSubjectCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeleteSubjectCommand.class.getName());
    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page = Pages.REDIRECT_VIEW_SUBJECTS;
        String subjectIdString = request.getParameter(Parameters.SUBJECT_ID);
        String subjectName=request.getParameter(Parameters.SUBJECT_NAME);

        SubjectService subjectService = (SubjectService) ServiceFactory.getService(ServiceType.SUBJECT_TYPE);
        try {
            int subjectId = Integer.parseInt(subjectIdString);
            subjectService.deleteSubject(subjectId);
            LOGGER.info("subject has been deleted: "+ subjectName);
        } catch (SubjectServiceException | NumberFormatException e) {
            LOGGER.error(e.getMessage());
            page= Pages.REDIRECT_ERROR_PAGE;
            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return page;
    }
}
