package command;

public enum CommandType {

    LOGIN(new LoginCommand()),
    NO_COMMAND(new NoCommand()),
    VIEW_FACULTIES(new ViewFacultiesCommand()),
    VIEW_FACULTY(new ViewFacultyCommand()),
    LOGOUT(new LogOutCommand()),
    REGISTRATION(new RegistrationCommand()),
    APPLY(new ApplyCommand()),
    PERSONAL_SETTINGS(new PersonalSettingsCommand()),
    VIEW_APPLICANTS(new ViewApplicantsCommand()),
    OPEN_ENROLLMENT(new OpenEnrollmentCommand()),
    CLOSE_ENROLLMENT(new CloseEnrollmentCommand()),
    VIEW_ENROLLMENTS(new ViewEnrollmentsCommand()),
    ERROR(new ErrorCommand()),
    EDIT_PROFILE(new EditProfileCommand()),
    CANCEL_APPLICATION(new CancelApplication()),
    VIEW_APPLICATION(new ViewApplicationCommand()),
    VIEW_SUBJECTS(new ViewSubjectsCommand()),
    ADD_SUBJECT(new AddSubjectCommand()),
    DELETE_SUBJECT(new DeleteSubjectCommand()),
    ADD_FACULTY(new AddFacultyCommand()),
    DELETE_FACULTY(new DeleteFacultyCommand());

    private final Command command;

    CommandType(Command command){
        this.command=command;
    }

    public Command getCommand(){
        return command;
    }
}
