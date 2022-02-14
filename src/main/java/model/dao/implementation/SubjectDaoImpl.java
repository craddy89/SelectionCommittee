package model.dao.implementation;

import model.dao.AbstractDao;
import model.dao.SubjectDao;
import model.dao.exception.SubjectDaoException;
import model.entity.Grade;
import model.entity.Subject;
import util.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl extends AbstractDao implements SubjectDao {

    private static final String FIND_SUBJECTS_BY_FACULTY_ID = "SELECT subject.id, subject.name FROM subject JOIN faculty, required_subject WHERE subject.id=required_subject.subject_id and faculty.id=required_subject.faculty_id and faculty.id=?";
    private static final String INSERT_SUBJECT_GRADES = "INSERT INTO subject_grades (applicant_id, subject_id, grade) VALUES (?,?,?)";
    private static final String DELETE_GRADES_BY_APPLICANT_ID = "DELETE FROM admissions_committee.subject_grades WHERE applicant_id=?";
    private static final String FIND_ALL_SUBJECTS = "SELECT subject.id, subject.name FROM admissions_committee.subject order by subject.id";
    private static final String FIND_SUBJECT_GRADE_BY_APPLICANT_ID = "SELECT subject.name, subject_grades.grade FROM admissions_committee.subject_grades JOIN subject ON subject.id=subject_grades.subject_id JOIN admissions_committee.applicant ON subject_grades.applicant_id=applicant.id WHERE applicant.id=?";
    private static final String INSERT_SUBJECT = "INSERT INTO admissions_committee.subject(subject.name) VALUES (?)";
    private static final String INSERT_REQUIRED_SUBJECTS = "INSERT INTO admissions_committee.required_subject (faculty_id, subject_id) VALUES(?,?)";
    private static final String DELETE_SUBJECT_BY_ID = "DELETE FROM admissions_committee.subject WHERE subject.id=?";
    private static final String DELETE_REQUIRED_SUBJECTS_BY_FACULTY_ID = "DELETE FROM admissions_committee.required_subject WHERE faculty_id=?";


    public List<Subject> getRequiredSubjects(Connection connection, int facultyId) throws SubjectDaoException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUBJECTS_BY_FACULTY_ID);
            preparedStatement.setInt(1, facultyId);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Subject> requiredSubjects = new ArrayList<>();

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setName(resultSet.getString(Fields.NAME));
                subject.setSubjectId(resultSet.getInt(Fields.ID));
                requiredSubjects.add(subject);
            }
            return requiredSubjects;
        } catch (SQLException e) {
            throw new SubjectDaoException("could not get required subjects: " + e.getMessage());
        }
    }

    public List<Subject> getSubjectGradesByApplicantId(Connection connection, int applicantId) throws SubjectDaoException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SUBJECT_GRADE_BY_APPLICANT_ID);
            preparedStatement.setInt(1, applicantId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Subject> subjects = new ArrayList<>();
            Subject subject;
            while (resultSet.next()) {
                subject = new Subject();
                subject.setName(resultSet.getString(Fields.NAME));
                subject.setGrade(resultSet.getInt(Fields.GRADE));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException e) {
            throw new SubjectDaoException("could not get subjects grades by applicant id:" + e.getMessage());
        }
    }

    public void deleteGradesByApplicantId(Connection connection, int applicantId) throws SubjectDaoException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GRADES_BY_APPLICANT_ID);
            preparedStatement.setInt(1, applicantId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SubjectDaoException("could not delete grades by id: " + e.getMessage());
        }
    }

    public List<Subject> getAllSubjects() throws SubjectDaoException {
        Connection connection = pool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SUBJECTS);
            List<Subject> subjects = new ArrayList<>();
            Subject subject;
            while (resultSet.next()) {
                subject = new Subject();
                subject.setSubjectId(resultSet.getInt(Fields.ID));
                subject.setName(resultSet.getString(Fields.NAME));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException e) {
            throw new SubjectDaoException("could not get all subjects: " + e.getMessage());
        } finally {
            pool.releaseConnection(connection);
        }
    }


    public void insertGrades(Connection connection, List<Grade> grades) throws SubjectDaoException {
        for (Grade grade : grades) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT_GRADES);

                preparedStatement.setInt(1, grade.getApplicantId());
                preparedStatement.setInt(2, grade.getSubjectId());
                preparedStatement.setInt(3, Integer.parseInt(grade.getGrade()));

                preparedStatement.execute();
            } catch (SQLException e) {
                throw new SubjectDaoException("could not insert grades " + e.getMessage());
            }
        }
    }

    public void insertRequiredSubjects(Connection connection, int facultyId, List<Subject> subjects) throws SubjectDaoException {
        for (Subject subject : subjects) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUIRED_SUBJECTS);
                preparedStatement.setInt(1, facultyId);
                preparedStatement.setInt(2, subject.getSubjectId());
                preparedStatement.execute();
            } catch (SQLException e) {
                throw new SubjectDaoException("could not insert required subjects: " + e.getMessage());
            }
        }
    }

    public boolean insert(Subject subject) throws SubjectDaoException {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECT);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new SubjectDaoException("could not insert subject: " + e.getMessage());
        } finally {
            pool.releaseConnection(connection);
        }
    }

    public boolean delete(int subjectId) throws SubjectDaoException {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUBJECT_BY_ID);
            preparedStatement.setInt(1, subjectId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new SubjectDaoException("could not delete subject by id: " + e.getMessage());
        } finally {
            pool.releaseConnection(connection);
        }
    }

    public void deleteRequiredSubjectsByFacultyId(Connection connection, int facultyId) throws SubjectDaoException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUIRED_SUBJECTS_BY_FACULTY_ID);
            preparedStatement.setInt(1, facultyId);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SubjectDaoException("could not delete required subjects by faculty id: " + e.getMessage());
        }
    }
}

