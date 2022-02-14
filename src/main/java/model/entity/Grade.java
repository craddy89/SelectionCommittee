package model.entity;

public class Grade {

    private String grade;
    private int subjectId;
    private int applicantId;

    public Grade(){

    }

    public Grade(String grade, int subjectId, int applicantId) {
        this.grade = grade;
        this.subjectId = subjectId;
        this.applicantId = applicantId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grade grade = (Grade) o;
        if (subjectId != grade.subjectId) {
            return false;
        }
        return applicantId == grade.applicantId;
    }

    @Override
    public int hashCode() {
        int result = subjectId;
        result = 31 * result + applicantId;
        return result;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "grade='" + grade + '\'' +
                '}';
    }
}

