package model.dao.validator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GradesValidatorTest {

    private String[] validGrades;
    private String[] invalidGrades;

    @BeforeClass
    public void setUp() {
        validGrades = new String[]{"15", "14", "62", "42"};
        invalidGrades = new String[]{"f41,140,521,ds"};
    }

    @Test
    public void testValidateGradesPositive() {
        assertTrue(GradesValidator.validateGrades(validGrades));
    }

    @Test
    public void testValidateGradesNegative() {
        assertFalse(GradesValidator.validateGrades(invalidGrades));
    }

}