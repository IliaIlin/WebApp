package org.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Саша on 26.03.2015.
 * @deprecated 
 * @see DataSourcePool
 */
public class DataSource {

    private Connection connection;

    final private static String URL = "jdbc:mysql://localhost:3309/db";
    final private static String DRIVER_NAME = "com.mysql.jdbc.Driver";

    /**
     * Gets connecion from server by using JDBC driver.
     * @param login 
     * @param password
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException 
     * @deprecated 
     */
    public DataSource(String login, String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Locale.setDefault(Locale.ENGLISH);

        Class.forName(DRIVER_NAME).newInstance();

        connection = DriverManager.getConnection(URL, login, password);
    }

    /**
     * 
     * @return connection with server
     * @deprecated 
     */
    public Connection getConnection() {
        return connection;
    }


}
