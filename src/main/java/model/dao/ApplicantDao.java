package model.dao;

import model.dao.exception.ApplicantDaoException;
import model.entity.Applicant;
import model.entity.Faculty;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

public interface ApplicantDao {

    TreeSet<Applicant> getApplicantsByEnrollment(Optional<Integer> enrollmentId) throws ApplicantDaoException;

    void updateEnrolledApplicantsState(Connection connection, List<Integer> idList) throws ApplicantDaoException;
    void updateNotEnrolledApplicantsState(Connection connection) throws ApplicantDaoException;

    Map<Faculty, TreeSet<Applicant>> getCurrentEnrollmentApplicants(Connection connection) throws ApplicantDaoException;
    List<Applicant> getApplicantsByUserId(int userId) throws ApplicantDaoException;
    List<Applicant> getApplicantsByUserId(Connection connection, int userId) throws ApplicantDaoException;
    List<Applicant> getApplicantsIdByFacultyId(Connection connection, int facultyId) throws ApplicantDaoException;
    Applicant getApplicantById(Connection connection, int applicantId) throws ApplicantDaoException;

    void deleteApplicantById(Connection connection, int applicantId) throws ApplicantDaoException;
    int insert(Connection connection, Applicant applicant) throws ApplicantDaoException;
}
