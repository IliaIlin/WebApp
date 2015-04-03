import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Саша on 04.03.2015.
 */
public class DataBaseStudentDaoImpl implements DataBaseStudentDao {

    private Connection connection;
    // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<Student> students;

    final private static String INSERT_STUDENT = "INSERT INTO STUDENTS VALUES ( ? , ? , to_date( ? , 'DD.MM.YY') , id.nextval, ?)";
    final private static String INSERT_STUDENT_WITHOUT_CURATOR = "INSERT INTO STUDENTS (NAME, GROUP_NUMBER, \"DATE\", ID) VALUES ( ? , ? , to_date( ? , 'DD.MM.YY') , id.nextval)";
    final private static String DELETE_STUDENT = "DELETE FROM STUDENTS WHERE ID = ?";
    final private static String SELECT_ALL_STUDENTS = "SELECT * FROM STUDENTS";
    //  final private static String SELECT_STUDENTS = "SELECT * FROM STUDENTS WHERE ID = ? AND GROUP_NUMBER = ? AND NAME = ? AND DATE = ?";
    final private static String SELECT_STUDENTS = "SELECT * FROM STUDENTS WHERE ";
    final private static String SET_GROUP = "UPDATE STUDENTS SET GROUP_NUMBER = ? WHERE ID = ?";
    final private static String SET_CURATOR = "UPDATE STUDENTS SET CURATOR = ? WHERE ID = ?";
    final private static String UPDATE_CURATOR = "UPDATE STUDENTS SET CURATOR = NULL WHERE CURATOR = ?";

    final private static int INDEX_COLUMB_NAME = 1;
    final private static int INDEX_COLUMB_GROUP_NUMBER = 2;
    final private static int INDEX_COLUMB_DATE = 3;
    final private static int INDEX_COLUMB_ID = 4;
    final private static int INDEX_COLUMB_ID_CURATOR = 5;

    public DataBaseStudentDaoImpl(Connection connection) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        this.connection = connection;

    }


    @Override
    public void insertStudent(String name, int numberGroup, String date, int idCurator) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_STUDENT);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(3, date);
        preparedStatement.setInt(4, idCurator);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void insertStudent(String name, int numberGroup, String date) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_STUDENT_WITHOUT_CURATOR);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(3, date);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void setGroup(int numberGroup, int id) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_GROUP);
        preparedStatement.setInt(1, numberGroup);
        preparedStatement.setInt(2, id);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void setCurator(int idCurator, int idStudent) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_CURATOR);
        preparedStatement.setInt(1, idCurator);
        preparedStatement.setInt(2, idStudent);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void deleteStudents(int id) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_STUDENT);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        // trigger not work!!!
        preparedStatement = connection.prepareStatement(UPDATE_CURATOR);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

    }


    @Override
    public ArrayList<Student> selectStudents(String param[], String arg[]) throws SQLException { //!!!!!!!!!!!!!!!!!!!!!!!!
        String statement = SELECT_STUDENTS;
        for (int i = 0; i < param.length; i++) {
            statement += " " + param[i] + " = ? ";
            if (i != param.length - 1) {
                statement += " AND ";
            }
        }
        preparedStatement = connection.prepareStatement(statement);
        for (int i = 0; i < arg.length; i++) {
            preparedStatement.setString(i + 1, arg[i]);
        }

        resultSet = preparedStatement.executeQuery();
        createStudents();
        return students;


    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException {
        preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
        resultSet = preparedStatement.executeQuery();
        createStudents();
        return students;
    }

    private void createStudents() throws SQLException {
        students = new ArrayList<>();
        Student student;
        while (resultSet.next()) {
            student = new Student(resultSet.getString(INDEX_COLUMB_NAME),
                    resultSet.getInt(INDEX_COLUMB_GROUP_NUMBER),
                    resultSet.getDate(INDEX_COLUMB_DATE),
                    resultSet.getLong(INDEX_COLUMB_ID),
                    resultSet.getLong(INDEX_COLUMB_ID_CURATOR));
            students.add(student);
        }
    }


}











