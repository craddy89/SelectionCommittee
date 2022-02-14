package model.entity;

import model.entity.role.UserRole;
import model.entity.state.ApplicantState;

import java.util.List;

public class Applicant extends User implements Comparable<Applicant> {

    private int id;
    private int facultyId;
    private String facultyName;
    private int enrollmentId;
    private ApplicantState applicantState;
    private int totalRating;
    private List<Subject> subjects;


    public Applicant() {

    }

    public Applicant(UserRole role, String login, int password, String firstName, String lastName, String patronymic, int id, int facultyId, String facultyName, int enrollmentId, ApplicantState applicantState, int totalRating, List<Subject> subjects) {
        super(role, login, password, firstName, lastName, patronymic);
        this.id = id;
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.enrollmentId = enrollmentId;
        this.applicantState = applicantState;
        this.totalRating = totalRating;
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public ApplicantState getApplicantState() {
        return applicantState;
    }

    public void setApplicantState(ApplicantState applicantState) {
        this.applicantState = applicantState;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }


    public int compareTo(Applicant applicant) {
//        return Integer.compare(applicant.totalRating, totalRating);
        return applicant.totalRating != totalRating ? Integer.compare(applicant.totalRating, totalRating) : Integer.compare(id, applicant.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Applicant applicant = (Applicant) o;
        return id == applicant.id;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "id=" + id +
                ", facultyName='" + facultyName + '\'' +
                ", enrollmentId=" + enrollmentId +
                ", applicantState=" + applicantState +
                ", totalRating=" + totalRating +
                '}';
    }
}

