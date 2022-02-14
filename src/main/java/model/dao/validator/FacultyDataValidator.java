package model.dao.validator;

public class FacultyDataValidator {

    private static final String FACULTY_DATA_PATTERN="[A-Z][a-z]{1,20}(\\s?[A-Za-z][a-z]{1,20})*";

    public static boolean validateFacultyName(String string){
        if(string!=null){
            return string.matches(FACULTY_DATA_PATTERN);
        }else{
            return false;
        }
    }

    public static boolean validateSubjectName(String string){
        return validateFacultyName(string);
    }

    public static boolean validateCapacity(String capacityString){

        try {
            int capacity=Integer.parseInt(capacityString);
            return capacity >= 2 && capacity <= 100;
        }catch (NumberFormatException e){
            return false;
        }


    }
}
