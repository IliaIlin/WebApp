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
import org.webapp.DataBaseStudentDaoImpl;
import org.webapp.DataSourcePool;
import org.webapp.Student;

/**
 *
 * @author Илья
 */
@Stateless
public class StudentBean implements StudentBeanLocal {

    DataSourcePool dataSource;
    DataBaseStudentDaoImpl studentDao;

    public StudentBean() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException {
        create();
    }

    // beginning of business logic

    @Override
    public void addStudentWithCurator(String name, int numberGroup, String date, long idCurator) throws SQLException {
        studentDao.insertStudent(name, numberGroup, date, idCurator);
    }

    @Override
    public void addStudentWithoutCurator(String name, int numberGroup, String date) throws SQLException {
        studentDao.insertStudent(name, numberGroup, date);
    }

    @Override
    public ArrayList<Student> getStudentsByCriterium(ArrayList<String> param, ArrayList<String> arg) throws SQLException, IOException, JAXBException {
        return studentDao.selectStudents(param, arg);
    }

    @Override
    public void removeStudents(ArrayList<Long> id) throws SQLException {
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
    public void exportStudents(FileWriter fileName, ArrayList<Long> id) throws JAXBException, SQLException, IOException {
        studentDao.exportStudents(fileName, id);
    }

    @Override
    public void importStudents(FileReader fileName) throws JAXBException, IOException, SQLException {
        studentDao.importStudents(fileName);
    }

    // end of business logic
    @Override
    public void create() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException {
        dataSource = new DataSourcePool();
        studentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
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
            Logger.getLogger(StudentBean.class.getName()).log(Level.SEVERE, null, ex);
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
