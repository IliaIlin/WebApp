/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Илья
 */
@Stateless
public class SessionBean implements SessionBeanLocal {

    DataSourcePool dataSource;
    DataBaseStudentDaoImpl studentDao;
    DataBaseGroupDaoImpl groupDao;

    @Override
    public void create() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException {
        dataSource = new DataSourcePool();
        studentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        groupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
    }

    @Override
    public void addGroup(int numberGroup, String faculty) throws SQLException {
        groupDao.insertGroup(numberGroup, faculty);
    }

    @Override
    public void removeGroups(long[] id) throws SQLException {
        groupDao.deleteGroups(id);
    }

    @Override
    public void editGroup(long id, String[] param, String[] arg) throws SQLException {
        groupDao.updateGroups(id, param, arg);
    }

    @Override
    public ArrayList<Group> getGroupsByCriterium(String param[], String arg[]) throws SQLException, IOException, JAXBException {
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
// ________________________________________________________________________
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Handle getHandle() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIdentical(EJBObject ejbo) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
