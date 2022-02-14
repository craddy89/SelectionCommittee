package filter.commandAccessFilter;

import model.entity.role.UserRole;

import javax.servlet.Filter;

public class ApplicantCommandFilter extends CommandAccessFilter implements Filter {

    {
        exclusiveCommands ="applicantCommands";
        userRole= UserRole.APPLICANT;
        logMessage="non-applicant user tried to perform: ";
    }
}
