package classes;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Саша on 26.03.2015.
 */
public class DataBaseGroupDaoImpl implements DataBaseGroupDao {

    private Connection connection;
    // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<Group> groups;

    final private static String INSERT_GROUP = "INSERT INTO GROUPS VALUES ( ? , ? )";
    final private static String DELETE_GROUP = "DELETE FROM GROUPS WHERE GROUP_NUMBER = ?";
    final private static String SELECT_ALL_GROUPS = "SELECT * FROM GROUPS";
    final private static String SELECT_GROUPS = "SELECT * FROM GROUPS WHERE ";
    final private static String UPDATE_GROUP = "UPDATE GROUPS SET FACULTY = ? WHERE GROUP_NUMBER = ?";

    final private static int INDEX_COLUMB_NUMBER_GROUP = 1;
    final private static int INDEX_COLUMB_FACULTY = 2;


    public DataBaseGroupDaoImpl(Connection connection) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        this.connection = connection;

    }


    @Override
    public void updateGroups(int numberGroup, String faculty) throws SQLException {
        preparedStatement = connection.prepareStatement(UPDATE_GROUP);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(1, faculty);
        resultSet = preparedStatement.executeQuery();


    }


    @Override
    public ResultSet insertGroup(int numberGroup, String faculty) throws SQLException {
        preparedStatement = connection.prepareStatement(INSERT_GROUP);
        preparedStatement.setInt(1, numberGroup);
        preparedStatement.setString(2, faculty);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    @Override
    public void deleteGroups(int numberGroup) throws SQLException {
        preparedStatement = connection.prepareStatement(DELETE_GROUP);
        preparedStatement.setInt(1, numberGroup);
        resultSet = preparedStatement.executeQuery();


    }


    @Override
    public ArrayList<Group> selectGroups(String param[], String arg[]) throws SQLException { //!!!!!!!!!!!!!!!!!!!!!!!!
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
        return groups;
    }


    @Override
    public ArrayList<Group> getAllGroups() throws SQLException {
        preparedStatement = connection.prepareStatement(SELECT_ALL_GROUPS);
        resultSet = preparedStatement.executeQuery();
        createGroups();
        return groups;
    }


    private void createGroups() throws SQLException {
        groups = new ArrayList<>();
        Group group;
        while (resultSet.next()) {
            group = new Group(resultSet.getInt(INDEX_COLUMB_NUMBER_GROUP),
                    resultSet.getString(INDEX_COLUMB_FACULTY));
            groups.add(group);
        }


    }
}
