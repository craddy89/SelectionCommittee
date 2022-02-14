package model.entity.role;

public enum UserRole {

    ADMIN(1),
    APPLICANT(2);

    private final int ordinalNumber;

    UserRole(int ordinalNumber){
        this.ordinalNumber=ordinalNumber;
    }

    public int getOrdinalNumber(){
        return ordinalNumber;
    }
}
