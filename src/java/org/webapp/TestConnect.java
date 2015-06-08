package org.webapp;

import org.webapp.xml.DataGroupAndStudents;
import org.webapp.xml.XmlWriteRead;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 18.03.2015.
 *
 * @deprecated
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException, NamingException, XMLStreamException {
        //DataSourcePool dataSource = new DataSourcePool();
        DataSource dataSource = new DataSource("root", "root");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());


        ArrayList<Group> groupsOld = XmlWriteRead.readGroups(new FileReader("groups.xml"));
        System.out.println(groupsOld.toString());
        //ArrayList<Student> studentsOld = XmlWriteRead.readStudents(new FileReader("students.xml"));
        
        //XmlWriteRead.writeGroupsAndStudents(groupsOld, studentsOld, new FileWriter("out.xml"));


          DataGroupAndStudents d = XmlWriteRead.readGroupAndStudents(new FileReader("out.xml"));
          ArrayList<Group> groupsNew= d.getGroups();
          ArrayList<Student> studentsNew = d.getStudents();
          dataBaseGroupDao.importGroups(groupsNew);
          dataBaseStudentDao.importStudents(studentsNew);
          
        //  System.out.println(groupsNew.toString());
        //  System.out.println(studentsNew.toString());
        // XmlWriteRead.readGroupAndStudents(null)
        // System.out.println(groupsNew.equals(groupsOld));
        //   System.out.println(studentsNew.equals(studentsOld));




    }


}
