package classes;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Антон
 */

public class DataSourcePool {
    
    private Connection connection;
    
    public DataSourcePool() throws NamingException, 
            SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        InitialContext ctx = new InitialContext();
        DataSource ds =(DataSource)ctx.lookup("java:jboss/jdbc/webapp");
        connection = ds.getConnection();
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void close() throws SQLException {
        connection.close();
    }
    
}
