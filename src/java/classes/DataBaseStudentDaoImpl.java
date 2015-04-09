import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 04.03.2015.
 */
public class DataBaseStudentDaoImpl implements DataBaseStudentDao {

    final private static String INSERT_STUDENT =
            "INSERT INTO STUDENTS_TEST " +
                    "VALUES ( ? , (SELECT ID FROM GROUPS_TEST WHERE GROUP_NUMBER = ? ) , " +
                    "to_date( ? , 'DD.MM.YY') , ID_STUDENTS.nextval, ?)";

    final private static String INSERT_STUDENT_WITHOUT_CURATOR =
            "INSERT INTO STUDENTS_TEST (NAME, GROUP_ID, \"DATE\", ID) " +
                    "VALUES ( ? , (SELECT ID FROM GROUPS_TEST WHERE GROUP_NUMBER = ? ) , " +
                    "to_date( ? , 'DD.MM.YY') , ID_STUDENTS.nextval)";

    final private static String DELETE_STUDENT = "DELETE FROM STUDENTS_TEST WHERE ID  IN ( ";

    final private static String SELECT_STUDENTS =
            "SELECT STUDENTS_TEST.\"NAME\", GROUPS_TEST.\"GROUP_NUMBER\", " +
                    "STUDENTS_TEST.\"DATE\",STUDENTS_TEST.\"ID\",STUDENTS_TEST.\"CURATOR\" " +
                    "FROM STUDENTS_TEST, GROUPS_TEST " +
                    "WHERE STUDENTS_TEST.\"GROUP_ID\" = GROUPS_TEST.\"ID\"";


    final private static String SET_GROUP = "UPDATE STUDENTS_TEST " +
            "SET GROUP_ID = (SELECT ID FROM GROUPS_TEST WHERE GROUP_NUMBER = ? ) WHERE ID = ?";

    final private static String SET_CURATOR = "UPDATE STUDENTS_TEST " +
            "SET CURATOR = ? WHERE ID = ?";

    final private static String SET_NAME = "UPDATE STUDENTS_TEST " +
            "SET NAME = ? WHERE ID = ?";

    final private static String SET_DATE = "UPDATE STUDENTS_TEST " +
            "SET \"DATE\" = ? WHERE ID = ?";

    final private static String UPDATE_CURATOR = "UPDATE STUDENTS_TEST " +
            "SET CURATOR = NULL WHERE CURATOR IN ( ";

    final private static int INDEX_COLUMB_NAME = 1;
    final private static int INDEX_COLUMB_ID_GROUP = 2;
    final private static int INDEX_COLUMB_DATE = 3;
    final private static int INDEX_COLUMB_ID = 4;
    final private static int INDEX_COLUMB_ID_CURATOR = 5;

    private Connection connection;
    // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<Student> students;

    public DataBaseStudentDaoImpl(Connection connection) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        this.connection = connection;

    }


    @Override
    public void insertStudent(String name, int numberGroup, String date, long idCurator) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_STUDENT);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(3, date);
        preparedStatement.setLong(4, idCurator);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void insertStudent(String name, int numberGroup, String date) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_STUDENT_WITHOUT_CURATOR);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(3, date);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void setGroup(int numberGroup, long id) throws SQLException {   //ok
        preparedStatement = connection.prepareStatement(SET_GROUP);
        preparedStatement.setInt(1, numberGroup);
        preparedStatement.setLong(2, id);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void setCurator(long idCurator, long idStudent) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(SET_CURATOR);
        preparedStatement.setLong(1, idCurator);
        preparedStatement.setLong(2, idStudent);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void deleteStudents(long id[]) throws SQLException {  //ok
        String statement = DELETE_STUDENT;
        if (id.length == 0) return;
        statement += " ?";
        for (int i = 1; i < id.length; i++) {
            statement += " , ?";
        }
        statement += ")";
        preparedStatement = connection.prepareStatement(statement);

        for (int i = 0; i < id.length; i++) {
            preparedStatement.setLong(i + 1, id[i]);
        }
        resultSet = preparedStatement.executeQuery();


        // trigger not work!!!
        statement = UPDATE_CURATOR;
        if (id.length == 0) return;
        statement += " ?";
        for (int i = 1; i < id.length; i++) {
            statement += " , ?";
        }
        statement += ")";
        preparedStatement = connection.prepareStatement(statement);

        for (int i = 0; i < id.length; i++) {
            preparedStatement.setLong(i + 1, id[i]);
        }
        resultSet = preparedStatement.executeQuery();

    }


    @Override
    public ArrayList<Student> selectStudents(String param[], String arg[]) throws SQLException {  //OK
        String statement = SELECT_STUDENTS;
        for (int i = 0; i < param.length; i++) {
            statement += " AND " + param[i] + " = ? ";
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
    public void setName(String name, long idStudent) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_NAME);
        preparedStatement.setString(1, name);
        preparedStatement.setLong(2, idStudent);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public void setDate(String date, long idStudent) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_DATE);
        preparedStatement.setString(1, date);
        preparedStatement.setLong(2, idStudent);
        resultSet = preparedStatement.executeQuery();
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException {  //OK
        preparedStatement = connection.prepareStatement(SELECT_STUDENTS);
        resultSet = preparedStatement.executeQuery();
        createStudents();
        return students;
    }

    private void createStudents() throws SQLException {
        students = new ArrayList<>();
        Student student;
        while (resultSet.next()) {
            student = new Student(resultSet.getString(INDEX_COLUMB_NAME),
                    resultSet.getInt(INDEX_COLUMB_ID_GROUP),
                    resultSet.getDate(INDEX_COLUMB_DATE),
                    resultSet.getLong(INDEX_COLUMB_ID),
                    resultSet.getLong(INDEX_COLUMB_ID_CURATOR));
            students.add(student);
        }
    }


}











