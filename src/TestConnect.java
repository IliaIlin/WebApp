import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 18.03.2015.
 */
public class TestConnect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        DataSource dataSource = new DataSource("PAI", "11");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDao dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
        //     dataBaseDao.getAllStudents();
        //   ResultSet resultSet = dataBaseDao.getResultSet();
        // resultSet.next();
        //     System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));

        // dataBaseDao.insertStudent("Pole", 1001, "11/11/11");
        // dataBaseDao.insertGroup(1002, "1");
        //     dataBaseDao.deleteGroups(1002);

        //   dataBaseDao.deleteStudents(5);
        //    dataBaseGroupDao.insertGroup(1002, "1");
        //   dataBaseStudentDao.insertStudent("ark", 1002,"11.11.11",11);
        //   dataBaseStudentDao.insertStudent("M", 1002,"11.11.11",11);
//        ResultSet resultSet = dataBaseStudentDao.selectStudents(new String[]{"GROUP_NUMBER"}, new String[]{"1001"});
//        int nameIndex = resultSet.findColumn("name");
//        int ageIndex = resultSet.findColumn("date");
//        int groupNumber = resultSet.findColumn("GROUP_NUMBER");
//        int id = resultSet.findColumn("ID");
//        int curator = resultSet.findColumn("CURATOR");
//
//
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(nameIndex) + " " + resultSet.getDate(ageIndex) + " "
//                    + resultSet.getString(groupNumber) + " " + resultSet.getString(id) + " "
//                    + resultSet.getString(curator));
//        }

        dataBaseStudentDao.insertStudent("sszczc", 1002, "11.11.21");
        ArrayList<Student> students = dataBaseStudentDao.getAllStudents();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(student.getNAME() + " --- " + student.getGROUP_STUDENT()
                    + " --- " + student.getDATE_ENROLLMENT() + " --- "
                    + student.getID() + " --- " + student.getID_CURATOR());
        }

    }
}
