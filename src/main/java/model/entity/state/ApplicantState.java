package model.entity.state;

public enum ApplicantState {

    APPLIED(1),
    ENROLLED(2),
    NOT_ENROLLED(3);

    private final int ordinalNumber;

    ApplicantState(int ordinalNumber ){
        this.ordinalNumber=ordinalNumber;
    }

    public int getOrdinalNumber(){
        return ordinalNumber;
    }

}
