package classes;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 18.03.2015.
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException, NamingException {
        //DataSourcePool dataSource = new DataSourcePool();
        DataSource dataSource = new DataSource("root", "11");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());

     //   dataBaseGroupDao.export("groups.xml", new long[]{7, 8});
   //     dataBaseStudentDao.export("students.xml", new long[]{1, 3, 5, 4, 9});

        dataBaseGroupDao.deleteGroups(new long[]{7, 8});
        ArrayList list = dataBaseGroupDao.getAllGroups();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        dataBaseGroupDao.imporT("groups.xml");
        System.out.println();
        list = dataBaseGroupDao.getAllGroups();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }


}
