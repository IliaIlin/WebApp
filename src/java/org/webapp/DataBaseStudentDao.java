package org.webapp;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 03.03.2015.
 */
public interface DataBaseStudentDao {

    public void insertStudent(String name, int numberGroup, String date, long idCurator) throws SQLException;

    public void insertStudent(String name, int numberGroup, String date) throws SQLException;


    public ArrayList<Student> selectStudents(ArrayList<String> param, ArrayList<String> arg) throws SQLException, IOException, JAXBException;

    public void deleteStudents(ArrayList<Long> id) throws SQLException;

    public ArrayList<Student> getAllStudents() throws SQLException, IOException, JAXBException;

    public void setGroup(int numberGroup, long id) throws SQLException;

    public void setCurator(long idCurator, long idStudent) throws SQLException;

    public void setName(String name, long idStudent) throws SQLException;

    public void setDate(String date, long idStudent) throws SQLException;

    public void importStudents(FileReader fileReader) throws JAXBException, IOException, SQLException;

    public void importStudents(ArrayList<Student> students) throws JAXBException, IOException, SQLException;

    public void exportStudents(FileWriter fileName, ArrayList<Long> id) throws JAXBException, SQLException, IOException;


}
