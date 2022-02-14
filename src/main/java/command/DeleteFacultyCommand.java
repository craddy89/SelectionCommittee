package command;

import controller.ActionType;
import model.service.FacultyService;
import model.service.exception.FacultyServiceException;
import model.service.factory.ServiceFactory;
import model.service.factory.ServiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;
import util.Pages;
import util.Parameters;

import javax.servlet.http.HttpServletRequest;

public class DeleteFacultyCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(DeleteFacultyCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType actionType) {

        String page= Pages.REDIRECT_ERROR_PAGE;
        int facultyId=Integer.parseInt(request.getParameter(Parameters.ID));
        String facultyName=request.getParameter(Parameters.FACULTY_NAME);

        FacultyService facultyService= (FacultyService) ServiceFactory.getService(ServiceType.FACULTY_SERVICE);
        try {
            facultyService.deleteFaculty(facultyId);
            LOGGER.info("faculty has been deleted: "+ facultyName);
            page=Pages.REDIRECT_VIEW_FACULTIES;
        } catch (FacultyServiceException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return page;
    }
}
