package model.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.configuration.ConfigurationData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());
    private static final int CONNECTIONS_INIT_NUMBER = 10;
    private static ConnectionPool instance;

    private BlockingQueue<Connection> connections;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public void initPool() {
        try {
            Class.forName(ConfigurationData.getString(ConfigurationData.DRIVER_PATH));
            connections = new ArrayBlockingQueue<>(CONNECTIONS_INIT_NUMBER);
            int successConnectionNumber = 0;

            for (int i = 0; i < CONNECTIONS_INIT_NUMBER; i++) {
                try {
                    connections.add(DriverManager.getConnection(ConfigurationData.getString(ConfigurationData.DATABASE_CONNECTION_PATH), ConfigurationData.getString(ConfigurationData.DATABASE_LOGIN), ConfigurationData.getString(ConfigurationData.DATABASE_PASSWORD)));
                    successConnectionNumber++;
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage());
                }
            }
            if (successConnectionNumber == 0) {
                connections = null;
                LOGGER.warn("Connection pool has been initialized with no one success connection");
            }else {
                LOGGER.info("Connection pool has been initialized with connections number: "+successConnectionNumber);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connections.take();
        } catch (NullPointerException e) {
            LOGGER.debug("there are no success connections in queue");
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connections.add(connection);
        }
    }

    public void closeAll() {
        if (connections != null) {
            for (Connection connection : connections) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("could not close connection: "+e.getMessage());
                }
            }
        }
    }
}

