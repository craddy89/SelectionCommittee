package listener;

import model.dao.connection.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        connectionPool.initPool();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        connectionPool.closeAll();
    }
}
