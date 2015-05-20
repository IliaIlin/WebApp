package org.webapp;

import org.webapp.xml.XmlWriteRead;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Саша on 18.03.2015.
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException, NamingException {
        //DataSourcePool dataSource = new DataSourcePool();
      /*  DataSource dataSource = new DataSource("root", "root");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
*/

        Student student = new Student("1", 123, new Date(), 11, 1);
        XmlWriteRead.test(student);
    }


}
