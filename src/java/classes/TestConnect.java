package classes;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Саша on 18.03.2015.
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException {
        DataSource dataSource = new DataSource("root", "11");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDao dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());

        dataBaseStudentDao.setDate("2011-01-11",81);

        System.out.println(dataBaseStudentDao.selectStudents(new String[]{"DATE"},new String[]{"2011-01-11"}));




        //  ArrayList<Integer> integers = dataBaseGroupDao.getGroupNumbers();
        //    System.out.println(integers.toString());

        //  System.out.println(idToDeleteNoEmpty[1]);
        //     dataBaseDao.getAllStudents();
        //   ResultSet resultSet = dataBaseDao.getResultSet();
        // resultSet.next();
        //     System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        // dataBaseDao.insertStudent("Pole", 1001, "11/11/11");
        // dataBaseDao.insertGroup(1002, "1");
        //     dataBaseDao.deleteGroups(1002);
        //   dataBaseDao.deleteStudents(5);
        //    dataBaseGroupDao.insertGroup(1002, "1");
        // ArrayList<Student> students = dataBaseStudentDao.selectStudents(new String[]{"NAME","NAME","GROUP_NUMBER"},new String[]{"M","ark","1007"});
        //      System.out.println(students);
//        ArrayList<Group> groups = dataBaseGroupDao.getAllGroups();
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.println(groups.get(i));
//        }
//        System.out.println();
//
        //  dataBaseGroupDao.updateGroups(27, new String[]{"GROUP_NUMBER", "FACULTY"}, new String[]{"1007", "qwert"});
//        dataBaseGroupDao.updateGroups(24, new String[]{"GROUP_NUMBER"},new String[]{"1010"});
//
//
//        groups = dataBaseGroupDao.getAllGroups();
//        for (int i = 0; i < groups.size(); i++) {
//            System.out.println(groups.get(i));
//        }
    }
}
