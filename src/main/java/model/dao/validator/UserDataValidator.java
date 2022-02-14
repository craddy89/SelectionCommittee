package model.dao.validator;

import model.entity.role.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Messages;

import java.util.ArrayList;
import java.util.List;

public class UserDataValidator {

    private static final Logger LOGGER = LogManager.getLogger(UserDataValidator.class.getName());

    private final static int VALIDATION_PARAMETERS_NUMBER = 7;

    private static final int LOGIN_ERROR_INDEX = 0;
    private static final int PASSWORD_ERROR_INDEX = 1;
    private static final int PASSWORDS_DIFFER_ERROR_INDEX = 2;
    private static final int SURNAME_ERROR_INDEX = 3;
    private static final int NAME_ERROR_INDEX = 4;
    private static final int PATRONYMIC_ERROR_INDEX = 5;
    private static final int USER_ROLE_ERROR_INDEX = 6;

    private static final String NAME_PATTERN = "[A-Z][a-z]{1,20}";
    private static final String PASSWORD_PATTERN = "(?=[a-zA-Z0-9]*[A-Z])[a-zA-Z0-9]{4,20}";
    private static final String LOGIN_PATTERN = ".+";

    private static final String[] ERROR_MESSAGES;

    private static final String[] userRoleNames;

    static {
        ERROR_MESSAGES = new String[VALIDATION_PARAMETERS_NUMBER];
        ERROR_MESSAGES[LOGIN_ERROR_INDEX] = Messages.INVALID_LOGIN;
        ERROR_MESSAGES[PASSWORD_ERROR_INDEX] = Messages.INVALID_PASSWORD;
        ERROR_MESSAGES[PASSWORDS_DIFFER_ERROR_INDEX] = Messages.INVALID_REPEAT_PASSWORD;
        ERROR_MESSAGES[SURNAME_ERROR_INDEX] = Messages.INVALID_NAME;
        ERROR_MESSAGES[NAME_ERROR_INDEX] = Messages.INVALID_NAME;
        ERROR_MESSAGES[PATRONYMIC_ERROR_INDEX] = Messages.INVALID_NAME;
        ERROR_MESSAGES[USER_ROLE_ERROR_INDEX] = Messages.INVALID_USER_ROLE;
    }

    static {
        UserRole[] userRoles = UserRole.values();
        userRoleNames = new String[userRoles.length];

        for (int i = 0; i < userRoleNames.length; i++) {
            userRoleNames[i] = userRoles[i].name();
        }
    }

    public static List<String> validateRegistrationForm(String login, String password, String repeatPassword, String surname, String name, String patronymic, String userRole) {

        boolean[] checks = new boolean[VALIDATION_PARAMETERS_NUMBER];
        checks[LOGIN_ERROR_INDEX] = UserDataValidator.validateLogin(login);
        checks[PASSWORD_ERROR_INDEX] = UserDataValidator.validatePassword(password);
        checks[PASSWORDS_DIFFER_ERROR_INDEX] = UserDataValidator.validateRepeatPassword(password, repeatPassword);
        checks[SURNAME_ERROR_INDEX] = UserDataValidator.validateNameComponent(surname);
        checks[NAME_ERROR_INDEX] = UserDataValidator.validateNameComponent(name);
        checks[PATRONYMIC_ERROR_INDEX] = UserDataValidator.validateNameComponent(patronymic);
        checks[USER_ROLE_ERROR_INDEX] = UserDataValidator.validateUserRole(userRole);


        return createErrorList(checks);
    }

    private static List<String> createErrorList(boolean[] checks) {

        List<String> errors = new ArrayList<>(VALIDATION_PARAMETERS_NUMBER);

        int errorNumber = 0;
        for (int i = 0; i < VALIDATION_PARAMETERS_NUMBER; i++) {
            if (!checks[i]) {
                LOGGER.info(ERROR_MESSAGES[i]);
                errors.add(ERROR_MESSAGES[i]);
                errorNumber++;
            } else {
                errors.add(null);
            }
        }

        return errorNumber == 0 ? null : errors;
    }

    public static List<String> validateEditingForm(String lastName, String firstName, String patronymic) {

        boolean[] checks = new boolean[VALIDATION_PARAMETERS_NUMBER];
        checks[LOGIN_ERROR_INDEX] = true;
        checks[PASSWORD_ERROR_INDEX] = true;
        checks[PASSWORDS_DIFFER_ERROR_INDEX] = true;
        checks[SURNAME_ERROR_INDEX] = UserDataValidator.validateNameComponent(lastName);
        checks[NAME_ERROR_INDEX] = UserDataValidator.validateNameComponent(firstName);
        checks[PATRONYMIC_ERROR_INDEX] = UserDataValidator.validateNameComponent(patronymic);
        checks[USER_ROLE_ERROR_INDEX] = true;
        return createErrorList(checks);
    }


    private static boolean validateNameComponent(String string) {
        if (string != null) {
            return string.matches(NAME_PATTERN);
        } else {
            return false;
        }
    }

    private static boolean validateLogin(String string) {
        if (string != null) {
            return string.matches(LOGIN_PATTERN);
        } else {
            return false;
        }
    }


    private static boolean validatePassword(String string) {
        if (string != null) {
            return string.matches(PASSWORD_PATTERN);
        } else {
            return false;
        }
    }

    private static boolean validateRepeatPassword(String string, String repeat) {
        if (string != null && repeat != null) {
            return string.equals(repeat);
        } else {
            return false;
        }
    }

    private static boolean validateUserRole(String userRoleString) {

        if (userRoleString != null) {
            for (String userRoleName : userRoleNames) {
                if (userRoleName.equalsIgnoreCase(userRoleString)) {
                    return true;
                }
            }
        }
        return false;
    }


}

