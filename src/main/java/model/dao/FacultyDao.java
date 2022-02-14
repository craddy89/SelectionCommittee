package model.dao;

import model.dao.exception.FacultyDaoException;
import model.entity.Faculty;

import java.sql.Connection;
import java.util.List;

public interface FacultyDao {

    List<Faculty> getAllFaculties() throws FacultyDaoException;
    Faculty getById(Connection connection, int facultyId) throws FacultyDaoException;

    int insert(Connection connection, Faculty faculty) throws FacultyDaoException;
    boolean delete(Connection connection, int facultyId) throws FacultyDaoException;
}
