package org.webapp.beans;

import javax.ejb.*;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.webapp.DataBaseGroupDaoImpl;
import org.webapp.DataBaseStudentDaoImpl;
import org.webapp.DataSourcePool;
import org.webapp.Group;
import org.webapp.Student;

/**
 * @author Илья
 */
@Stateless
public class WebAppBean implements WebAppLocal {

    DataSourcePool dataSource;
    DataBaseStudentDaoImpl studentDao;
    DataBaseGroupDaoImpl groupDao;

    public WebAppBean() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException {
        create();
    }

    @Override
    public void create() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException {
        dataSource = new DataSourcePool();
        studentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
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

    // here starts business logic
    // business logic for groups
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

    // business logic for students
    @Override
    public void addStudentWithCurator(String name, int numberGroup, String date, long idCurator) throws SQLException {
        studentDao.insertStudent(name, numberGroup, date, idCurator);
    }

    @Override
    public void addStudentWithoutCurator(String name, int numberGroup, String date) throws SQLException {
        studentDao.insertStudent(name, numberGroup, date);
    }

    @Override
    public ArrayList<Student> getStudentsByCriterium(String[] param, String[] arg) throws SQLException, IOException, JAXBException {
        return studentDao.selectStudents(param, arg);
    }

    @Override
    public void removeStudents(long[] id) throws SQLException {
        studentDao.deleteStudents(id);
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException, IOException, JAXBException {
        return studentDao.getAllStudents();
    }

    @Override
    public void editGroupOfStudent(int numberGroup, long id) throws SQLException {
        studentDao.setGroup(numberGroup, id);
    }

    @Override
    public void editCuratorOfStudent(long idCurator, long idStudent) throws SQLException {
        studentDao.setCurator(idCurator, idStudent);
    }

    @Override
    public void editNameOfStudent(String name, long idStudent) throws SQLException {
        studentDao.setName(name, idStudent);
    }

    @Override
    public void editDateOfStudent(String date, long idStudent) throws SQLException {
        studentDao.setDate(date, idStudent);
    }

    @Override
    public void exportStudents(String fileName, long[] id) throws JAXBException, SQLException, IOException {
        studentDao.export(fileName, id);
    }

    @Override
    public void exportGroups(String fileName, long[] id) throws JAXBException, SQLException, IOException {
        groupDao.export(fileName, id);
    }

    @Override
    public void importStudents(String fileName) throws JAXBException, IOException, SQLException {
        studentDao.imporT(fileName);
    }

    @Override
    public void importGroups(String fileName) throws JAXBException, SQLException, IOException {
        groupDao.imporT(fileName);
    }
}
