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
import org.webapp.Student;

/**
 *
 * @author Илья
 */
@Local
public interface StudentBeanLocal extends EJBObject{

    public void create() throws ClassNotFoundException, NamingException, SQLException, InstantiationException, IllegalAccessException;

    public void addStudentWithCurator(String name, int numberGroup, String date, long idCurator) throws SQLException;

    public void addStudentWithoutCurator(String name, int numberGroup, String date) throws SQLException;

    public ArrayList<Student> getStudentsByCriterium(ArrayList<String> param, ArrayList<String> arg) throws SQLException, IOException, JAXBException;

    public void removeStudents(ArrayList<Long> id) throws SQLException;

    public ArrayList<Student> getAllStudents() throws SQLException, IOException, JAXBException;

    public void editGroupOfStudent(int numberGroup, long id) throws SQLException;

    public void editCuratorOfStudent(long idCurator, long idStudent) throws SQLException;

    public void editNameOfStudent(String name, long idStudent) throws SQLException;

    public void editDateOfStudent(String date, long idStudent) throws SQLException;

    public void exportStudents(FileWriter fileName, ArrayList<Long> id) throws JAXBException, SQLException, IOException;

    public void importStudents(FileReader fileName) throws JAXBException, IOException, SQLException;
}
