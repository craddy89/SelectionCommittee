package filter.commandAccessFilter;

import model.entity.role.UserRole;

import javax.servlet.Filter;

public class AdminCommandFilter extends CommandAccessFilter implements Filter {

    {
        exclusiveCommands ="adminCommands";
        userRole= UserRole.ADMIN;
        logMessage="non-admin user tried to perform: ";
    }
}
