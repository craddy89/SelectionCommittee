package model.service;

import model.dao.exception.UserDaoException;
import model.entity.User;
import model.service.exception.UserServiceException;

import java.sql.Connection;

public class UserService extends Service {



    public User login(String login) throws UserServiceException {
        try {
            return userDao.getByLogin(login);
        } catch (UserDaoException e) {
            logger.error(e.getMessage());
            throw new UserServiceException(e.getMessage());
        }


    }

    public int addUser(Connection connection, User user) throws UserServiceException {
        try {
            return userDao.insert(connection, user);
        } catch (UserDaoException e) {
            logger.error(e.getMessage());
            throw new UserServiceException(e.getMessage());
        }
    }

    public boolean editUser(User user) throws UserServiceException {
        try {
            return userDao.update(user);
        } catch (UserDaoException e) {
            logger.error(e.getMessage());
            throw new UserServiceException(e.getMessage());
        }
    }

    public boolean checkLoginUniqueness(Connection connection, String login) throws UserServiceException {

        try {
            User user = userDao.getByLogin(connection, login);
            return user == null;

        } catch (UserDaoException e) {
            logger.error(e.getMessage());
            throw new UserServiceException(e.getMessage());
        }
    }
}

