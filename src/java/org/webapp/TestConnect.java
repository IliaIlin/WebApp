package org.webapp;

import org.webapp.xml.XmlWriteRead;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Саша on 18.03.2015.
 * @deprecated 
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException, NamingException, XMLStreamException {
        //DataSourcePool dataSource = new DataSourcePool();
        DataSource dataSource = new DataSource("root", "11");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());



        XmlWriteRead.writeGroupsAndStudents(XmlWriteRead.readGroups(new FileReader("groups.xml")),
                XmlWriteRead.readStudents(new FileReader("students.xml")),new FileWriter("out.xml"));

        XmlWriteRead.readGroupAndStudents(new FileReader("out.xml"));

    }


}
