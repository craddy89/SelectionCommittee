package model.entity.state;

public enum EnrollmentState {

    OPENED(1),
    CLOSED(2);

    private final int ordinalNumber;

    EnrollmentState(int ordinalNumber){
        this.ordinalNumber=ordinalNumber;
    }
    public int getOrdinalNumber(){
        return ordinalNumber;
    }
}