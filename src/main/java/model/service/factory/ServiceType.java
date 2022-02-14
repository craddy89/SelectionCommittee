package model.service.factory;

import model.service.*;

public enum ServiceType {

    FACULTY_SERVICE(new FacultyService()),
    USER_SERVICE(new UserService()),
    ENROLLMENT_SERVICE(new EnrollmentService()),
    SUBJECT_TYPE(new SubjectService()),
    APPLICANT_SERVICE(new ApplicantService());
    private Service service;

    ServiceType(Service service){
        this.service=service;
    }

    public Service getService(){
        return service;
    }
}
