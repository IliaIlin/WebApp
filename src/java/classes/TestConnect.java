package classes;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 * Created by Саша on 18.03.2015.
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException, NamingException {
        DataSourcePool dataSource = new DataSourcePool();
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());

        dataBaseGroupDao.export("groups.xml");        

        ArrayList list = dataBaseGroupDao.getAllGroups();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


}
