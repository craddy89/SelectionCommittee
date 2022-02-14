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
import java.util.List;

public class ViewFacultiesCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ViewFacultyCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, ActionType type) {

        String page= Pages.FORWARD_ERROR_PAGE;

        FacultyService facultyService = (FacultyService) ServiceFactory.getService(ServiceType.FACULTY_SERVICE);

        try {
            List<Faculty> faculties = facultyService.getAllFaculties();
            if(!faculties.isEmpty()) {
                LOGGER.info("faculties were found and set as attribute");
                request.setAttribute(Parameters.FACULTIES, faculties);
            }else{
                LOGGER.warn("faculties weren't found");
            }
            page= Pages.FORWARD_VIEW_FACULTIES;
        } catch (FacultyServiceException e) {
            LOGGER.error(e.getMessage());
            request.getSession().setAttribute(Parameters.ERROR, Messages.INTERNAL_ERROR);
        }
        return page;
    }
}
