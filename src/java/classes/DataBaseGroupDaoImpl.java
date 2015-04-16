import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 26.03.2015.
 */
public class DataBaseGroupDaoImpl implements DataBaseGroupDao {

    final private static String INSERT_GROUP = "INSERT INTO GROUPS_TEST VALUES ( ? , ? , ID_GROUP.nextval)";
    final private static String DELETE_GROUP = "DELETE FROM GROUPS_TEST WHERE ID IN (";
    final private static String SELECT_ALL_GROUPS = "SELECT * FROM GROUPS_TEST";
    final private static String SELECT_GROUPS = "SELECT * FROM GROUPS_TEST WHERE ";
    final private static String SELECT_GROUP_NUMBERS = "SELECT GROUP_NUMBER FROM GROUPS_TEST";
    final private static String SELECT_EMPTY_GROUP_NUMBERS = "SELECT ID FROM GROUPS_TEST " +
            "WHERE ID NOT IN (SELECT GROUP_ID FROM STUDENTS_TEST)";
    final private static String UPDATE_GROUP = "UPDATE GROUPS_TEST SET ";
    final private static int INDEX_COLUMB_NUMBER_GROUP = 1;
    final private static int INDEX_COLUMB_FACULTY = 2;
    final private static int INDEX_COLUMB_ID = 3;
    private Connection connection;
    // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<Group> groups;


    public DataBaseGroupDaoImpl(Connection connection) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        this.connection = connection;

    }


    @Override
    public void updateGroups(long id, String param[], String arg[]) throws SQLException {  //ok
        String statement = UPDATE_GROUP;
        for (int i = 0; i < param.length; i++) {
            statement += param[i] + " = ? ";
            if (i != param.length - 1)
                statement += " , ";
        }
        statement += " WHERE ID = ?";
        preparedStatement = connection.prepareStatement(statement);
        int i;
        for (i = 1; i <= arg.length; i++) {
            preparedStatement.setString(i, arg[i - 1]);
        }
        preparedStatement.setLong(i, id);
        resultSet = preparedStatement.executeQuery();


    }


    @Override
    public ResultSet insertGroup(int numberGroup, String faculty) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_GROUP);
        preparedStatement.setInt(1, numberGroup);
        preparedStatement.setString(2, faculty);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    @Override
    public void deleteGroups(long[] id) throws SQLException {  //ok
        String statement = DELETE_GROUP;
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
    public ArrayList<Group> selectGroups(String param[], String arg[]) throws SQLException, IOException, JAXBException { //????
        String statement = SELECT_GROUPS;
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
        createGroups();
        Xml.write(groups);
        return groups;
    }


    @Override
    public ArrayList<Group> getAllGroups() throws SQLException, IOException, JAXBException {  //ok
        preparedStatement = connection.prepareStatement(SELECT_ALL_GROUPS);
        resultSet = preparedStatement.executeQuery();
        createGroups();
        Xml.write(groups);
        return groups;
    }


    private void createGroups() throws SQLException{
        groups = new ArrayList<>();
        Group group;
        while (resultSet.next()) {
            group = new Group(resultSet.getInt(INDEX_COLUMB_NUMBER_GROUP),
                    resultSet.getString(INDEX_COLUMB_FACULTY),
                    resultSet.getLong(INDEX_COLUMB_ID));
            groups.add(group);
        }

    }

    @Override
    public ArrayList<Integer> getGroupNumbers() throws SQLException {
        preparedStatement = connection.prepareStatement(SELECT_GROUP_NUMBERS);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Integer> groups = new ArrayList<>();
        while (resultSet.next()) {
            groups.add(resultSet.getInt(INDEX_COLUMB_NUMBER_GROUP));
        }
        return groups;
    }

    @Override
    public ArrayList<Long> getEmptyGroupIDs() throws SQLException {
        preparedStatement = connection.prepareStatement(SELECT_EMPTY_GROUP_NUMBERS);
        resultSet = preparedStatement.executeQuery();
        ArrayList<Long> groups = new ArrayList<>();
        while (resultSet.next()) {
            groups.add(resultSet.getLong(INDEX_COLUMB_NUMBER_GROUP));
        }
        return groups;
    }


}
