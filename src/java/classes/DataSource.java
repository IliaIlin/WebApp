package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by Саша on 26.03.2015.
 */
public class DataSource {

    private Connection connection;

    final private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final private static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

    public DataSource(String login, String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Locale.setDefault(Locale.ENGLISH);

        Class.forName(DRIVER_NAME).newInstance();

        connection = DriverManager.getConnection(URL, login, password);
    }

    public Connection getConnection(){
        return connection;
    }
}
