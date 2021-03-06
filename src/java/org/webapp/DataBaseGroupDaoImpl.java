package org.webapp;

import org.webapp.xml.XmlWriteRead;

import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Саша on 26.03.2015.
 * <p>
 * This is Data Access Object class which works with the objects of groups.
 * </p>
 *
 * @see Group
 */
public class DataBaseGroupDaoImpl implements DataBaseGroupDao {

    final private static String INSERT_GROUP = "INSERT INTO GROUPS (GROUP_NUMBER, FACULTY) VALUES ( ? , ?)";
    final private static String DELETE_GROUP = "DELETE FROM GROUPS WHERE ID_GROUP IN (";
    final private static String SELECT_ALL_GROUPS = "SELECT * FROM GROUPS";
    final private static String SELECT_GROUPS = "SELECT * FROM GROUPS WHERE ";
    final private static String SELECT_GROUP_NUMBERS = "SELECT GROUP_NUMBER FROM GROUPS";
    final private static String SELECT_EMPTY_GROUP_NUMBERS = "SELECT ID_GROUP FROM GROUPS " +
            "WHERE ID_GROUP NOT IN (SELECT ID_GROUP FROM STUDENTS)";
    final private static String UPDATE_GROUP = "UPDATE GROUPS SET ";

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


    /**
     * This method updates list of groups using a list of string parameters.
     *
     * @param id
     * @param param
     * @param arg
     * @throws SQLException
     */
    @Override
    public void updateGroups(long id, ArrayList<String> param, ArrayList<String> arg) throws SQLException {  //ok
        String statement = UPDATE_GROUP;
        for (int i = 0; i < param.size(); i++) {
            statement += param.get(i) + " = ? ";
            if (i != param.size() - 1)
                statement += " , ";
        }
        statement += " WHERE ID_GROUP = ?";
        preparedStatement = connection.prepareStatement(statement);
        int i;
        for (i = 1; i <= arg.size(); i++) {
            preparedStatement.setString(i, arg.get(i - 1));
        }
        preparedStatement.setLong(i, id);
        preparedStatement.executeUpdate();
    }

    /**
     * This method updates list of groups using two string parameters.
     *
     * @param id
     * @param param
     * @param arg
     * @throws SQLException
     */
    private void updateGroups(int numberGroup, String param, String arg) throws SQLException {  //ok
        String statement = UPDATE_GROUP;
        statement += param + " = ? ";
        statement += " WHERE GROUP_NUMBER = ?";

        preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, arg);
        preparedStatement.setLong(2, numberGroup);
        preparedStatement.executeUpdate();
    }

    /**
     * Inserts new object of group to the group's list by number and faculty of the new group.
     *
     * @param numberGroup
     * @param faculty
     * @throws SQLException
     */
    @Override
    public void insertGroup(int numberGroup, String faculty) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_GROUP);
        preparedStatement.setInt(1, numberGroup);
        preparedStatement.setString(2, faculty);
        preparedStatement.executeUpdate();
    }

    /**
     * Deletes groups from the group's list by the list of their IDs.
     *
     * @param id
     * @throws SQLException
     */
    @Override
    public void deleteGroups(ArrayList<Long> id) throws SQLException {  //ok
        String statement = DELETE_GROUP;
        if (id.size() == 0) return;
        statement += " ?";
        for (int i = 1; i < id.size(); i++) {
            statement += " , ?";
        }
        statement += ")";
        preparedStatement = connection.prepareStatement(statement);

        for (int i = 0; i < id.size(); i++) {
            preparedStatement.setLong(i + 1, id.get(i));
        }

        preparedStatement.executeUpdate();


    }

    /**
     * Selects groups by a list of its parameters.
     *
     * @param param
     * @param arg
     * @return list of selected groups
     * @throws SQLException
     * @throws IOException
     * @throws JAXBException
     */
    @Override
    public ArrayList<Group> selectGroups(ArrayList<String> param, ArrayList<String> arg) throws SQLException, IOException, JAXBException {
        if(param.size() != arg.size())
            return new ArrayList<Group>();
        String statement = SELECT_GROUPS;
        for (int i = 0; i < param.size(); i++) {
            statement += " " + param.get(i) + " = ? ";
            if (i != param.size() - 1) {
                statement += " AND ";
            }
        }

        preparedStatement = connection.prepareStatement(statement);
        for (int i = 0; i < arg.size(); i++) {
            preparedStatement.setString(i + 1, arg.get(i));
        }

        resultSet = preparedStatement.executeQuery();
        createGroups();
        return groups;
    }

    /**
     * Gets a list of all groups.
     *
     * @return list of all groups
     * @throws SQLException
     * @throws IOException
     * @throws JAXBException
     */
    @Override
    public ArrayList<Group> getAllGroups() throws SQLException, IOException, JAXBException {
        preparedStatement = connection.prepareStatement(SELECT_ALL_GROUPS);
        resultSet = preparedStatement.executeQuery();
        createGroups();
//          exportGroups();
        return groups;
    }

    /**
     * Creates a list of new groups.
     *
     * @throws SQLException
     */
    private void createGroups() throws SQLException {
        groups = new ArrayList<>();
        Group group;
        while (resultSet.next()) {
            group = new Group(resultSet.getInt(INDEX_COLUMB_NUMBER_GROUP),
                    resultSet.getString(INDEX_COLUMB_FACULTY),
                    resultSet.getLong(INDEX_COLUMB_ID));
            groups.add(group);
        }

    }

    /**
     * Gets numbers of all groups.
     *
     * @return list of groups' numbers
     * @throws SQLException
     */
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

    /**
     * Gets numbers of all empty groups.
     *
     * @return list of numbers of empty groups
     * @throws SQLException
     */
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

    /**
     * Exports a list of groups by its IDs to the file.
     *
     * @param fileWriter
     * @param id
     * @throws JAXBException
     * @throws IOException
     * @throws SQLException
     */
    @Override
    public void exportGroups(FileWriter fileWriter, ArrayList<Long> id) throws JAXBException, IOException, SQLException {
        String statement = SELECT_ALL_GROUPS;
        if (id.size() == 0) return;
        statement += " WHERE ID_GROUP IN ( ? ";
        for (int i = 1; i < id.size(); i++) {
            statement += " , ? ";
        }
        statement += ")";
        System.out.println(statement);
        preparedStatement = connection.prepareStatement(statement);

        for (int i = 0; i < id.size(); i++) {
            preparedStatement.setLong(i + 1, id.get(i));
        }
        resultSet = preparedStatement.executeQuery();
        createGroups();
        XmlWriteRead.writeGroups(fileWriter, groups);
    }


    /**
     * Imports a list of groups from the file.
     *
     * @param fileReader
     * @throws JAXBException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public void importGroups(FileReader fileReader) throws JAXBException, SQLException, IOException {
        importGroups(XmlWriteRead.readGroups(fileReader));
    }

    /**
     * Imports a list of groups from the file.
     *
     * @param groups
     * @throws JAXBException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public void importGroups(ArrayList<Group> groups) throws JAXBException, SQLException, IOException {
        ArrayList<Group> groupsInTable = getAllGroups();
        ArrayList<Group> groupsExport = groups;
        ArrayList<Group> listAdd = new ArrayList<>();
        ArrayList<Group> listSet = new ArrayList<>();
        Set<Integer> setNumbersGroups = new TreeSet<>();

        for (int i = 0; i < groupsInTable.size(); i++) {
            setNumbersGroups.add(groupsInTable.get(i).getGroupNumber());
        }


        for (int i = 0; i < groupsExport.size(); i++) {
            boolean isRemoved = false;
            Group group = groupsExport.get(i);
            for (int j = 0; j < groupsInTable.size(); j++) {
                if (groupsInTable.get(j).equals(group)) {
                    groupsInTable.remove(j);
                    isRemoved = true;
                    break;
                }
            }
            if (!isRemoved) {
                if (setNumbersGroups.contains(group.getGroupNumber())) {
                    listSet.add(group);
                } else {
                    listAdd.add(group);
                }

            }
        }
        for (int i = 0; i < listAdd.size(); i++) {
            Group group = listAdd.get(i);
            insertGroup(group.getGroupNumber(), group.getFaculty());
        }
        for (int i = 0; i < listSet.size(); i++) {
            Group group = listSet.get(i);
            updateGroups(group.getGroupNumber(), "FACULTY", group.getFaculty());
        }

    }
}
