package model.dao.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class UserDataValidatorTest {

    private String validLogin;
    private String validPassword;
    private String validRepeatPassword;
    private String validLastName;
    private String validFirstName;
    private String validPatronymic;
    private String validUserRole;

    private String invalidLogin;
    private String invalidPassword;
    private String invalidRepeatPassword;
    private String invalidLastName;
    private String invalidFirstName;
    private String invalidPatronymic;
    private String invalidUserRole;


    @BeforeClass
    public void setUp() {
        validLogin = "login";
        validPassword = "Password";
        validRepeatPassword = "Password";
        validLastName = "Lastname";
        validFirstName = "Firstname";
        validPatronymic = "Patronymic";
        validUserRole = "Applicant";

        invalidLogin = "lo&gin";
        invalidPassword = "password";
        invalidRepeatPassword = "pssword";
        invalidLastName = "LastName";
        invalidFirstName = "FirstName;";
        invalidPatronymic = "patronymic";
        invalidUserRole = "someRole";
    }

    @Test
    public void testValidateRegistrationFormPositive() {
        assertNull(UserDataValidator.validateRegistrationForm(validLogin, validPassword, validRepeatPassword, validLastName, validFirstName, validPatronymic, validUserRole));
    }

    @Test
    public void testValidateRegistrationFormNegative() {
        assertNotNull(UserDataValidator.validateRegistrationForm(invalidLogin, invalidPassword, invalidRepeatPassword, invalidLastName, invalidFirstName, invalidPatronymic, invalidUserRole));
    }
}
