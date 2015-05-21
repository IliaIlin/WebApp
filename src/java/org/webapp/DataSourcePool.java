package org.webapp;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *The class gets and returns a connection from the pool.
 * @author Антон
 */

public class DataSourcePool {
    
    private Connection connection;
    
    /**
     * This constructor gets a connection from the pool by the next JNDI name "java:jboss/datasources/WebApp".
     * @throws NamingException
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException 
     */
    public DataSourcePool() throws NamingException, 
            SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        InitialContext ctx = new InitialContext();
        //DataSource ds =(DataSource)ctx.lookup("java:jboss/jdbc/webapp");
        DataSource ds =(DataSource)ctx.lookup("java:jboss/datasources/WebApp");
        connection = ds.getConnection();
    }
    
    /**
     * Getting connection from the pool.
     * @return connection from pool
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * This method closes got connection and returns it to pool.
     * @throws SQLException 
     */
    public void close() throws SQLException {
        connection.close();
    }
    
}
