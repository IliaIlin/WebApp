/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.beans;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJBObject;
import javax.ejb.Local;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import org.webapp.Group;

/**
 *
 * @author Илья
 */
@Local
public interface GroupBeanLocal extends EJBObject {

    public void create() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException;

    public void addGroup(int numberGroup, String faculty) throws SQLException;

    public void removeGroups(ArrayList<Long> id) throws SQLException;

    public void editGroup(long id, ArrayList<String> param, ArrayList<String> arg) throws SQLException;

    public ArrayList<Group> getGroupsByCriterium(ArrayList<String> param, ArrayList<String> arg) throws SQLException, IOException, JAXBException;

    public ArrayList<Group> getAllGroups() throws SQLException, IOException, JAXBException;

    public ArrayList<Integer> getGroupNumbers() throws SQLException;

    public ArrayList<Long> getEmptyGroupIDs() throws SQLException;

    public void exportGroups(FileWriter fileName, ArrayList<Long> id) throws JAXBException, SQLException, IOException;

    public void importGroups(FileReader fileName) throws JAXBException, SQLException, IOException;
}
