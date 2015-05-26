package org.webapp;

import java.io.FileReader;
import java.io.FileWriter;
import org.webapp.xml.XmlWriteRead;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 18.03.2015.
 * @deprecated 
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException, NamingException, XMLStreamException {
        //DataSourcePool dataSource = new DataSourcePool();
        DataSource dataSource = new DataSource("root", "root");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());



       // XmlWriteRead.writeGroupsAndStudents(XmlWriteRead.readGroups(new FileReader("groups.xml")),
        //        XmlWriteRead.readStudents(new FileReader("students.xml")),new FileWriter("out.xml"));

        //XmlWriteRead.readGroupAndStudents(new FileReader("out.xml"));
        ArrayList<String> param=new ArrayList<>();
        ArrayList<String> arg=new ArrayList<>();
        param.add("GROUP_NUMBER");
        arg.add("1001");
        ArrayList<Group> selectGroups = dataBaseGroupDao.selectGroups(param, arg);
        //arg.clear();
        //arg.add("9602");
        ArrayList<Student> selectStudents=dataBaseStudentDao.selectStudents(param, arg);
        System.out.println(selectGroups.toString());
        System.out.println(selectStudents.toString());
        //XmlWriteRead.writeGroupsAndStudents(selectGroups,selectStudents,new FileWriter("output.xml"));
        XmlWriteRead.readGroupAndStudents(new FileReader("output.xml"));
    }


}
