package util;

public class Pages {

    public static final String FORWARD_MAIN = "/jsp/main.jsp";
    public static final String REDIRECT_MAIN = "";

    public static final String FORWARD_PERSONAL_SETTINGS = "jsp/personal_settings.jsp";
    public static final String REDIRECT_PERSONAL_SETTINGS = "Committee?command=personal_settings";

    public static final String FORWARD_VIEW_FACULTIES = "/jsp/view_faculties.jsp";
    public static final String REDIRECT_VIEW_FACULTIES = "Committee?command=view_faculties";

    public static final String FORWARD_ERROR_PAGE = "/jsp/error.jsp";
    public static final String REDIRECT_ERROR_PAGE = "Committee?command=error";

    public static final String FORWARD_VIEW_SUBJECTS = "/jsp/view_subjects.jsp";
    public static final String REDIRECT_VIEW_SUBJECTS = "Committee?command=view_subjects";

    public static final String FORWARD_ADD_FACULTY = "/jsp/add_faculty.jsp";
    public static final String REDIRECT_ADD_FACULTY = "Committee?command=add_faculty";

    public static final String FORWARD_VIEW_APPLICANTS = "/jsp/view_applicants.jsp";
    public static final String FORWARD_VIEW_FACULTY = "/jsp/view_faculty.jsp";
    public static final String FORWARD_APPLY_PAGE = "/jsp/apply.jsp";
    public static final String FORWARD_VIEW_ENROLLMENTS = "/jsp/view_enrollments.jsp";
    public static final String FORWARD_EDIT_PROFILE = "/jsp/edit_profile.jsp";
    public static final String FORWARD_VIEW_APPLICATION = "/jsp/view_application.jsp";

    public static final String FORWARD_REGISTRATION = "/jsp/registration.jsp";
    public static final String REDIRECT_REGISTRATION = "Committee?command=registration";
}
