package org.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Саша on 26.03.2015.
 */
public class DataSource {

    private Connection connection;

    final private static String URL = "jdbc:mysql://localhost:3306/db";
    final private static String DRIVER_NAME = "com.mysql.jdbc.Driver";

    public DataSource(String login, String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Locale.setDefault(Locale.ENGLISH);

        Class.forName(DRIVER_NAME).newInstance();

        connection = DriverManager.getConnection(URL, login, password);
    }

    public Connection getConnection() {
        return connection;
    }


}
