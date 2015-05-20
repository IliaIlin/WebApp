package org.webapp;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 26.03.2015.
 */
public interface DataBaseGroupDao {

    public void insertGroup(int numberGroup, String faculty) throws SQLException;

    public void deleteGroups(ArrayList<Long> id) throws SQLException;

    public void updateGroups(long id, ArrayList<String> param, ArrayList<String> arg) throws SQLException;

    public ArrayList<Group> selectGroups(ArrayList<String> param, ArrayList<String> arg) throws SQLException, IOException, JAXBException;

    public ArrayList<Group> getAllGroups() throws SQLException, IOException, JAXBException;

    public ArrayList<Integer> getGroupNumbers() throws SQLException;

    public ArrayList<Long> getEmptyGroupIDs() throws SQLException;

    public void exportGroups(String fileName, ArrayList<Long> id) throws JAXBException, IOException, SQLException;

    public void importGroups(String fileName) throws JAXBException, SQLException, IOException;


    }
