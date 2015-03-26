package java.ForClasses;

import java.sql.*;
import java.util.Locale;

/**
 * Created by Саша on 04.03.2015.
 */
public class DataBaseStudentDaoImpl implements DataBaseStudentDao {

    private Connection connection;
   // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    final private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    final private static String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

    final private static String INSERT_STUDENT = "INSERT INTO STUDENTS VALUES ( ? , ? , to_date( ? , 'DD.MM.YY') , id.nextval)";
    final private static String DELETE_STUDENT = "DELETE FROM STUDENTS WHERE ID = ?";
    final private static String SELECT_ALL_STUDENTS = "SELECT * FROM STUDENTS";
    final private static String SELECT_STUDENTS = "SELECT * FROM STUDENTS WHERE ID = ? AND GROUP_NUMBER = ? AND NAME = ? AND DATE = ?";

    public DataBaseStudentDaoImpl(String login, String password) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Locale.setDefault(Locale.ENGLISH);

        Class.forName(DRIVER_NAME).newInstance();

        connection = DriverManager.getConnection(URL, login, password);
//        statement = connection.createStatement();

    }


    @Override
    public void updateStudents(String s) {

    }


    @Override
    public void insertStudent(String name, int numberGroup, String date) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_STUDENT);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(3, date);
        resultSet = preparedStatement.executeQuery();
    }


    @Override
    public void deleteStudents(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_STUDENT);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

    }


    @Override
    public void selectStudents(String s) throws SQLException { //!!!!!!!!!!!!!!!!!!!!!!!!
        preparedStatement = connection.prepareStatement(SELECT_STUDENTS);
        resultSet = preparedStatement.executeQuery();



    }

    @Override
    public void getAllStudents() throws SQLException {
        preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
        resultSet = preparedStatement.executeQuery();
    }


    public ResultSet getResultSet() {
        return resultSet;
    }

}
