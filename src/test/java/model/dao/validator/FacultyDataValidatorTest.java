package model.dao.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FacultyDataValidatorTest {

    private String validFacultyName;
    private String invalidFacultyName;

    private String validCapacity;
    private String invalidCapacity;

    @BeforeClass
    public void setUp() {
        validFacultyName="Faculty of Something";
        invalidFacultyName="faculty";

        validCapacity="33";
        invalidCapacity="f21";
    }


    @Test
    public void testValidateFacultyNamePositive() {
        assertTrue(FacultyDataValidator.validateFacultyName(validFacultyName));
    }

    @Test
    public void testValidateFacultyNameNegative() {
        assertFalse(FacultyDataValidator.validateFacultyName(invalidFacultyName));
    }

    @Test
    public void testValidateCapacityPositive() {
        assertTrue(FacultyDataValidator.validateCapacity(validCapacity));
    }

    @Test
    public void testValidateCapacityNegative() {
        assertFalse(FacultyDataValidator.validateCapacity(invalidCapacity));
    }
}
