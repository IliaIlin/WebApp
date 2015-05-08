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
public interface WebAppLocal extends EJBObject{

    public void create() throws ClassNotFoundException,NamingException,SQLException,InstantiationException,IllegalAccessException;

    public void addGroup(int numberGroup, String faculty) throws SQLException;

    public void removeGroups(long[] id) throws SQLException;

    public void editGroup(long id, String[] param, String[] arg) throws SQLException;

    public ArrayList<Group> getGroupsByCriterium(String param[], String arg[]) throws SQLException,IOException,JAXBException;

    public ArrayList<Group> getAllGroups() throws SQLException,IOException,JAXBException;

    public ArrayList<Integer> getGroupNumbers() throws SQLException;

    public ArrayList<Long> getEmptyGroupIDs() throws SQLException;
    
    public void addStudentWithCurator(String name, int numberGroup, String date, long idCurator) throws SQLException;

    public void addStudentWithoutCurator(String name, int numberGroup, String date) throws SQLException;

    public ArrayList<Student> getStudentsByCriterium(String param[], String arg[]) throws SQLException, IOException, JAXBException;

    public void removeStudents(long id[]) throws SQLException;

    public ArrayList<Student> getAllStudents() throws SQLException, IOException, JAXBException;

    public void editGroupOfStudent(int numberGroup, long id) throws SQLException;

    public void editCuratorOfStudent(long idCurator, long idStudent) throws SQLException;

    public void editNameOfStudent(String name, long idStudent) throws SQLException;

    public void editDateOfStudent(String date, long idStudent) throws SQLException;
    
    public void exportStudents(String fileName, long id[]) throws JAXBException, SQLException, IOException;
    
    public void exportGroups(String fileName, long id[]) throws JAXBException, SQLException, IOException;

}
