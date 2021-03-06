package org.webapp;


import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void exportGroups(FileWriter fileWriter, ArrayList<Long> id) throws JAXBException, IOException, SQLException;

    public void importGroups(ArrayList<Group> groups) throws JAXBException, SQLException, IOException;

    public void importGroups(FileReader fileReader) throws JAXBException, SQLException, IOException;


}
