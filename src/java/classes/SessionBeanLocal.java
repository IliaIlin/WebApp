/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJBObject;
import javax.ejb.Local;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Илья
 */
@Local
public interface SessionBeanLocal extends EJBObject{

    public void create() throws ClassNotFoundException,NamingException,SQLException,InstantiationException,IllegalAccessException;

    public void addGroup(int numberGroup, String faculty) throws SQLException;

    public void removeGroups(long[] id) throws SQLException;

    public void editGroup(long id, String[] param, String[] arg) throws SQLException;

    public ArrayList<Group> getGroupsByCriterium(String param[], String arg[]) throws SQLException,IOException,JAXBException;

    public ArrayList<Group> getAllGroups() throws SQLException,IOException,JAXBException;

    public ArrayList<Integer> getGroupNumbers() throws SQLException;

    public ArrayList<Long> getEmptyGroupIDs() throws SQLException;
    
    
}
