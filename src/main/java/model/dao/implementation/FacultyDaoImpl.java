package model.dao.implementation;

import model.dao.AbstractDao;
import model.dao.FacultyDao;
import model.dao.exception.FacultyDaoException;
import model.entity.Faculty;
import util.Fields;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImpl extends AbstractDao implements FacultyDao {

    private static final String FIND_ALL = "SELECT id, name, capacity FROM admissions_committee.faculty";
    private static final String FIND_BY_ID = "SELECT id, name, capacity FROM admissions_committee.faculty WHERE id=?";
    private static final String INSERT_FACULTY="INSERT INTO admissions_committee.faculty (faculty.name, faculty.capacity) VALUES (?,?)";
    private static final String DELETE_BY_ID="DELETE FROM admissions_committee.faculty WHERE id=?";

    @Override
    public List<Faculty> getAllFaculties() throws FacultyDaoException {
        Connection connection=pool.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);

            List<Faculty> faculties = new ArrayList<>();

            while (resultSet.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getInt(Fields.ID));
                faculty.setName(resultSet.getString(Fields.NAME));
                faculty.setCapacity(resultSet.getInt(Fields.CAPACITY));
                faculties.add(faculty);
            }
            return faculties;
        } catch (SQLException e) {
            throw new FacultyDaoException("could not get faculties: " + e.getMessage());
        }finally {
            pool.releaseConnection(connection);
        }

    }

    public Faculty getById(Connection connection,int facultyId) throws FacultyDaoException {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, facultyId);
            ResultSet resultSet = preparedStatement.executeQuery();

            Faculty faculty = null;
            while (resultSet.next()) {
                faculty = new Faculty();
                faculty.setId(resultSet.getInt(Fields.ID));
                faculty.setName(resultSet.getString(Fields.NAME));
                faculty.setCapacity(resultSet.getInt(Fields.CAPACITY));
            }
            return faculty;

        } catch (SQLException e) {
            throw new  FacultyDaoException("could not get faculty by facultyId: "+e.getMessage());
        }
    }

    public int insert(Connection connection,Faculty faculty) throws FacultyDaoException {
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(INSERT_FACULTY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,faculty.getName());
            preparedStatement.setInt(2,faculty.getCapacity());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        } catch (SQLException e) {
            throw new FacultyDaoException("could not insert faculty: " +e.getMessage());
        }
    }

    public boolean delete(Connection connection,int facultyId) throws FacultyDaoException {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1,facultyId);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new FacultyDaoException("could not delete faculty by id: "+e.getMessage());
        }
    }
}

