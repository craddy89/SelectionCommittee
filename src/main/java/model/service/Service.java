package model.service;

import model.dao.*;
import model.dao.connection.ConnectionPool;
import model.dao.factory.DaoFactory;
import model.dao.factory.DaoType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Service {

    protected ConnectionPool pool;
    protected Logger logger;

    ApplicantDao applicantDao;
    EnrollmentDao enrollmentDao;
    FacultyDao facultyDao;
    UserDao userDao;
    SubjectDao subjectDao;

    {
        logger = LogManager.getRootLogger();
        pool = ConnectionPool.getInstance();
        applicantDao = (ApplicantDao) DaoFactory.getDao(DaoType.APPLICANT_DAO);
        enrollmentDao = (EnrollmentDao) DaoFactory.getDao(DaoType.ENROLLMENT_DAO);
        facultyDao = (FacultyDao) DaoFactory.getDao(DaoType.FACULTY_DAO);
        userDao = (UserDao) DaoFactory.getDao(DaoType.USER_DAO);
        subjectDao = (SubjectDao) DaoFactory.getDao(DaoType.SUBJECT_DAO);
    }
}

