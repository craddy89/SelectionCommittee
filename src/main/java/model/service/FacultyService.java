package model.service;

import model.dao.exception.ApplicantDaoException;
import model.dao.exception.DaoException;
import model.dao.exception.FacultyDaoException;
import model.dao.exception.SubjectDaoException;
import model.entity.Applicant;
import model.entity.Faculty;
import model.service.exception.FacultyServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FacultyService extends Service {

    public List<Faculty> getAllFaculties() throws FacultyServiceException {
        try {
            return facultyDao.getAllFaculties();
        } catch (FacultyDaoException e) {
            logger.error(e.getMessage());
            throw new FacultyServiceException(e.getMessage());
        }
    }

    public Faculty getById(int facultyId) throws FacultyServiceException {
        Connection connection = pool.getConnection();
        Faculty faculty;
        try {
            faculty = facultyDao.getById(connection, facultyId);
            if (faculty != null) {
                faculty.setRequiredSubjects(subjectDao.getRequiredSubjects(connection, faculty.getId()));
            }
            return faculty;
        } catch (FacultyDaoException | SubjectDaoException e) {
            logger.error(e.getMessage());
            throw new FacultyServiceException(e.getMessage());
        } finally {
            pool.releaseConnection(connection);
        }
    }

    public void addFaculty(Faculty faculty) throws FacultyServiceException {
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
            try {
                int newFacultyId = facultyDao.insert(connection,faculty);
                subjectDao.insertRequiredSubjects(connection,newFacultyId,faculty.getRequiredSubjects());
                connection.commit();
            } catch (FacultyDaoException | SubjectDaoException e) {
                connection.rollback();
                throw new DaoException(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (DaoException | SQLException e) {
            logger.error(e.getMessage());
            throw new FacultyServiceException(e.getMessage());
        } finally {
            pool.releaseConnection(connection);
        }
    }

    public void deleteFaculty(int facultyId) throws FacultyServiceException {
        Connection connection = pool.getConnection();
        try {
            connection.setAutoCommit(false);
            try {
                List<Applicant> applicants = applicantDao.getApplicantsIdByFacultyId(connection, facultyId);
                if (!applicants.isEmpty()) {
                    for (Applicant applicant : applicants) {
                        subjectDao.deleteGradesByApplicantId(connection, applicant.getId());
                        applicantDao.deleteApplicantById(connection, applicant.getId());
                    }
                }
                subjectDao.deleteRequiredSubjectsByFacultyId(connection, facultyId);
                facultyDao.delete(connection, facultyId);
                connection.commit();
            } catch (FacultyDaoException | SubjectDaoException | ApplicantDaoException e) {
                connection.rollback();
                throw new DaoException((e.getMessage()));
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (DaoException | SQLException e) {
            logger.error(e.getMessage());
            throw new FacultyServiceException(e.getMessage());
        } finally {
            pool.releaseConnection(connection);
        }
    }
}
