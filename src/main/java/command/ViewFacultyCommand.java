package command;

import controller.ActionType;
import model.entity.Faculty;
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

public class ViewFacultyCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(ViewFacultyCommand.class.getName());


    @Override
    public String execute(HttpServletRequest request, ActionType type) {

        String page = Pages.FORWARD_ERROR_PAGE;

        int facultyId = Integer.parseInt(request.getParameter(Parameters.ID));
        FacultyService facultyService = (FacultyService) ServiceFactory.getService(ServiceType.FACULTY_SERVICE);

        try {
            Faculty faculty = facultyService.getById(facultyId);
            if(faculty!=null) {
                LOGGER.info("faculty and required subjects were found and set as attribute");
                request.setAttribute(Parameters.FACULTY, faculty);
                page = Pages.FORWARD_VIEW_FACULTY;
            }else{
                LOGGER.warn("faculty wasn't found");
            }
        } catch (FacultyServiceException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }

        return page;
    }
}
