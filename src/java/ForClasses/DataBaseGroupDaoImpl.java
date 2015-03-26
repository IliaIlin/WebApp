package java.ForClasses;

import java.sql.*;
import java.util.Locale;

/**
 * Created by Саша on 26.03.2015.
 */
public class DataBaseGroupDaoImpl implements DataBaseGroupDao {

    private Connection connection;
    // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    final private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final private static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";


    final private static String INSERT_GROUP = "INSERT INTO GROUPS VALUES ( ? , ? )";
    final private static String DELETE_GROUP = "DELETE FROM GROUPS WHERE GROUP_NUMBER = ?";
    final private static String SELECT_ALL_GROUPS = "SELECT * FROM GROUPS";
    final private static String SELECT_GROUPS = "SELECT * FROM GROUPS WHERE GROUP_NUMBER = ? AND FACULTY = ?";

    public DataBaseGroupDaoImpl(String login, String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Locale.setDefault(Locale.ENGLISH);

        Class.forName(DRIVER_NAME).newInstance();

        connection = DriverManager.getConnection(URL, login, password);
//        statement = connection.createStatement();

    }


    @Override
    public void updateGroups(String s) {

    }


    @Override
    public void insertGroup(int numberGroup, String faculty) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_GROUP);
        preparedStatement.setInt(1, numberGroup);
        preparedStatement.setString(2, faculty);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void deleteGroups(int numberGroup) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_GROUP);
        preparedStatement.setInt(1, numberGroup);
        resultSet = preparedStatement.executeQuery();


    }


    @Override
    public void selectGroups(String s) throws SQLException { //!!!!!!!!!!!!!!!!!!!!!!!!
        preparedStatement = connection.prepareStatement(SELECT_GROUPS);


        resultSet = preparedStatement.executeQuery();
    }


    @Override
    public void getAllGroups() throws SQLException {
        preparedStatement = connection.prepareStatement(SELECT_ALL_GROUPS);
        resultSet = preparedStatement.executeQuery();
    }


    public ResultSet getResultSet() {
        return resultSet;
    }

}
