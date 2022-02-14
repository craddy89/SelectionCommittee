package model.dao;

import model.dao.exception.SubjectDaoException;
import model.entity.Grade;
import model.entity.Subject;

import java.sql.Connection;
import java.util.List;

public interface SubjectDao {
    List<Subject> getAllSubjects() throws SubjectDaoException;

    List<Subject> getRequiredSubjects(Connection connection, int facultyId) throws SubjectDaoException;
    List<Subject> getSubjectGradesByApplicantId(Connection connection, int applicantId) throws SubjectDaoException;
    void insertGrades(Connection connection, List<Grade> grades) throws SubjectDaoException;
    void insertRequiredSubjects(Connection connection, int facultyId, List<Subject> subjects) throws SubjectDaoException;
    void deleteGradesByApplicantId(Connection connection, int applicantId) throws SubjectDaoException;
    void deleteRequiredSubjectsByFacultyId(Connection connection, int facultyId) throws SubjectDaoException;

    boolean insert(Subject subject) throws SubjectDaoException;
    boolean delete(int subjectId) throws SubjectDaoException;
}
