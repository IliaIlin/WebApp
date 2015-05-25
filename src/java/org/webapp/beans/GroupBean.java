/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.webapp.beans;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import org.webapp.DataBaseGroupDaoImpl;
import org.webapp.DataSourcePool;
import org.webapp.Group;

/**
 *
 * @author Илья
 */
@Stateless
public class GroupBean implements GroupBeanLocal {

    DataSourcePool dataSource;
    DataBaseGroupDaoImpl groupDao;

    public GroupBean() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException {
        create();
    }
    // beginning of business logic

    @Override
    public void addGroup(int numberGroup, String faculty) throws SQLException {
        groupDao.insertGroup(numberGroup, faculty);
    }

    @Override
    public void removeGroups(ArrayList<Long> id) throws SQLException {
        groupDao.deleteGroups(id);
    }

    @Override
    public void editGroup(long id, ArrayList<String> param, ArrayList<String> arg) throws SQLException {
        groupDao.updateGroups(id, param, arg);
    }

    @Override
    public ArrayList<Group> getGroupsByCriterium(ArrayList<String> param, ArrayList<String> arg) throws SQLException, IOException, JAXBException {
        return groupDao.selectGroups(param, arg);
    }

    @Override
    public ArrayList<Group> getAllGroups() throws SQLException, IOException, JAXBException {
        return groupDao.getAllGroups();
    }

    @Override
    public ArrayList<Integer> getGroupNumbers() throws SQLException {
        return groupDao.getGroupNumbers();
    }

    @Override
    public ArrayList<Long> getEmptyGroupIDs() throws SQLException {
        return groupDao.getEmptyGroupIDs();
    }

    @Override
    public void exportGroups(FileWriter fileName, ArrayList<Long> id) throws JAXBException, SQLException, IOException {
        groupDao.exportGroups(fileName, id);
    }

    @Override
    public void importGroups(FileReader fileName) throws JAXBException, SQLException, IOException {
        groupDao.importGroups(fileName);
    }

    // end of business logic
    @Override
    public void create() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException {
        dataSource = new DataSourcePool();
        groupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
    }

    @Override
    public EJBHome getEJBHome() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getPrimaryKey() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() throws RemoteException, RemoveException {
        try {
            dataSource.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebAppBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Handle getHandle() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIdentical(EJBObject ejbo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
