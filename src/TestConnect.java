import java.sql.SQLException;

/**
 * Created by Саша on 18.03.2015.
 */
public class TestConnect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        DataSource dataSource = new DataSource("PAI", "11");
        DataBaseStudentDaoImpl dataBaseDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
   //     dataBaseDao.getAllStudents();
     //   ResultSet resultSet = dataBaseDao.getResultSet();
       // resultSet.next();
   //     System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));

       // dataBaseDao.insertStudent("Pole", 1001, "11/11/11");
       // dataBaseDao.insertGroup(1002, "1");
   //     dataBaseDao.deleteGroups(1002);

        //   dataBaseDao.deleteStudents(5);

        dataBaseDao.insertStudent("Mark", 1001,"11.11.11",11);
    }
}
