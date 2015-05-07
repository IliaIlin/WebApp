package classes;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Саша on 04.03.2015.
 */
public class DataBaseStudentDaoImpl implements DataBaseStudentDao {

    final private static String INSERT_STUDENT
            = "INSERT INTO STUDENTS (NAME, ID_GROUP, DATE, CURATOR) "
            + "VALUES ( ? , (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) , "
            + // "to_date( ? , 'DD.MM.YY') , ID_STUDENTS.nextval, ?)";
            //STR_TO_DATE('21,5,2013','%d,%m,%Y')
            "STR_TO_DATE( ? , '%Y-%m-%d') , ?)";


    final private static String INSERT_STUDENT_WITH_ID__WITHOUT_CURATOR
            = "INSERT INTO STUDENTS (ID_STUDENT, NAME, ID_GROUP, DATE) "
            + "VALUES (? , ? , (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) , "
            + "?)";

    final private static String INSERT_STUDENT_WITH_ID
            = "INSERT INTO STUDENTS (ID_STUDENT, NAME, ID_GROUP, DATE, CURATOR) "
            + "VALUES (? , ? , (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) , "
            + "? , ?)";


    final private static String INSERT_STUDENT_WITHOUT_CURATOR
            = "INSERT INTO STUDENTS (NAME, ID_GROUP, DATE) "
            + "VALUES ( ? , (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) , "
            + //  "to_date( ? , 'DD.MM.YY') , ID_STUDENTS.nextval)";
            "STR_TO_DATE( ? , '%Y-%m-%d'))";

    final private static String DELETE_STUDENT = "DELETE FROM STUDENTS WHERE ID_STUDENT  IN ( ";

    final private static String SELECT_STUDENTS =
            "SELECT STUDENTS.NAME, GROUPS.GROUP_NUMBER, "
                    + "STUDENTS.DATE,STUDENTS.ID_STUDENT,STUDENTS.CURATOR "
                    + "FROM STUDENTS, GROUPS "
                    + "WHERE STUDENTS.ID_GROUP = GROUPS.ID_GROUP";

    final private static String SET_GROUP = "UPDATE STUDENTS "
            + "SET ID_GROUP = (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) WHERE ID_STUDENT = ?";

    final private static String SET_CURATOR = "UPDATE STUDENTS "
            + "SET CURATOR = ? WHERE ID_STUDENT = ?";

    final private static String SET_NAME = "UPDATE STUDENTS "
            + "SET NAME = ? WHERE ID_STUDENT = ?";

    final private static String SET_DATE = "UPDATE STUDENTS "
            + "SET DATE = STR_TO_DATE( ? , '%Y-%m-%d') WHERE ID_STUDENT = ?";

    final private static String UPDATE_CURATOR = "UPDATE STUDENTS "
            + "SET CURATOR = NULL WHERE CURATOR IN ( ";

    final private static String SET_STUDENTS = "UPDATE STUDENTS "
            + "SET  NAME = ? , ID_GROUP = (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) , DATE = ? ,CURATOR = ? WHERE ID_STUDENT = ?";

    final private static String SET_STUDENTS_WITHOUT_CURATOR = "UPDATE STUDENTS "
            + "SET  NAME = ? , ID_GROUP = (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) , DATE = ? WHERE ID_STUDENT = ?";




    final private static int INDEX_COLUMB_NAME = 1;
    final private static int INDEX_COLUMB_ID_GROUP = 2;
    final private static int INDEX_COLUMB_DATE = 3;
    final private static int INDEX_COLUMB_ID = 4;
    final private static int INDEX_COLUMB_ID_CURATOR = 5;

    final private static String NAME = "NAME";
    final private static String GROUP_NUMBER = "GROUP_NUMBER";
    final private static String DATE = "DATE";
    final private static String CURATOR = "CURATOR";

    final private ArrayList<Integer> nameParam = new ArrayList<>();
    final private ArrayList<Integer> groupParam = new ArrayList<>();
    final private ArrayList<Integer> dateParam = new ArrayList<>();
    final private ArrayList<Integer> curatorParam = new ArrayList<>();

    private Connection connection;
    // private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private ArrayList<Student> students;

    public DataBaseStudentDaoImpl(Connection connection) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        this.connection = connection;

    }

    @Override
    public void insertStudent(String name, int numberGroup, String date, long idCurator) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_STUDENT);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(3, date);
        preparedStatement.setLong(4, idCurator);
        preparedStatement.executeUpdate();
    }

    @Override
    public void insertStudent(String name, int numberGroup, String date) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_STUDENT_WITHOUT_CURATOR);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setString(3, date);
        preparedStatement.executeUpdate();
    }


    private void insertStudent(long id, String name, int numberGroup, Date date, long idCurator) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_STUDENT_WITH_ID);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, numberGroup);
        preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
        preparedStatement.setLong(5, idCurator);
        preparedStatement.executeUpdate();
    }


    private void insertStudent(long id, String name, int numberGroup, Date date) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(INSERT_STUDENT_WITH_ID__WITHOUT_CURATOR);
        preparedStatement.setLong(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, numberGroup);
        preparedStatement.setDate(4, new java.sql.Date(date.getTime()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void setGroup(int numberGroup, long id) throws SQLException {   //ok
        preparedStatement = connection.prepareStatement(SET_GROUP);
        preparedStatement.setInt(1, numberGroup);
        preparedStatement.setLong(2, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void setCurator(long idCurator, long idStudent) throws SQLException {  //ok
        preparedStatement = connection.prepareStatement(SET_CURATOR);
        if (idCurator == 0) {
            preparedStatement.setObject(1, null);
        } else {
            preparedStatement.setLong(1, idCurator);
        }
        preparedStatement.setLong(2, idStudent);
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteStudents(long id[]) throws SQLException {  //ok
        String statement = DELETE_STUDENT;
        if (id.length == 0) {
            return;
        }
        statement += " ?";
        for (int i = 1; i < id.length; i++) {
            statement += " , ?";
        }
        statement += ")";
        preparedStatement = connection.prepareStatement(statement);

        for (int i = 0; i < id.length; i++) {
            preparedStatement.setLong(i + 1, id[i]);
        }
        preparedStatement.executeUpdate();

        // trigger not work!!!
        statement = UPDATE_CURATOR;
        if (id.length == 0) {
            return;
        }
        statement += " ?";
        for (int i = 1; i < id.length; i++) {
            statement += " , ?";
        }
        statement += ")";
        preparedStatement = connection.prepareStatement(statement);

        for (int i = 0; i < id.length; i++) {
            preparedStatement.setLong(i + 1, id[i]);
        }
        preparedStatement.executeUpdate();

    }

    @Override
    public ArrayList<Student> selectStudents(String param[], String arg[]) throws SQLException, IOException, JAXBException {  //OK
        String statement = SELECT_STUDENTS;
        for (int i = 0; i < param.length; i++) {
            switch (param[i].toUpperCase()) {
                case NAME:
                    nameParam.add(i);
                    break;
                case GROUP_NUMBER:
                    groupParam.add(i);
                    break;
                case DATE:
                    dateParam.add(i);
                    break;
                case CURATOR:
                    curatorParam.add(i);
                    break;
            }
        }

        if (nameParam.size() > 0) {
            statement += " AND STUDENTS.NAME IN ( ?";
            for (int i = 1; i < nameParam.size(); i++) {
                statement += " , ? ";
            }
            statement += " )";
        }
        if (dateParam.size() > 0) {
            statement += " AND STUDENTS.DATE IN ( STR_TO_DATE( ? , '%Y-%m-%d') ";
            for (int i = 1; i < dateParam.size(); i++) {
                statement += " , ? ";
            }
            statement += " )";
        }
        if (curatorParam.size() > 0) {
            statement += " AND STUDENTS.CURATOR IN ( ? ";
            for (int i = 1; i < curatorParam.size(); i++) {
                statement += " , ? ";
            }
            statement += " )";
        }
        if (groupParam.size() > 0) {
            statement += " AND GROUPS.ID_GROUP IN ( (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) ";
            for (int i = 1; i < groupParam.size(); i++) {
                statement += " , (SELECT ID_GROUP FROM GROUPS WHERE GROUP_NUMBER = ? ) ";
            }
            statement += " )";
        }

        preparedStatement = connection.prepareStatement(statement);
        int j = 1;
        for (int i = 0; i < nameParam.size(); i++) {
            preparedStatement.setString(j++, arg[nameParam.get(i)]);
        }
        for (int i = 0; i < dateParam.size(); i++) {
            preparedStatement.setString(j++, arg[dateParam.get(i)]);
        }
        for (int i = 0; i < curatorParam.size(); i++) {
            preparedStatement.setString(j++, arg[curatorParam.get(i)]);
        }
        for (int i = 0; i < groupParam.size(); i++) {
            preparedStatement.setString(j++, arg[groupParam.get(i)]);
        }

        resultSet = preparedStatement.executeQuery();
        createStudents();
        return students;
    }

    @Override
    public void setName(String name, long idStudent) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_NAME);
        preparedStatement.setString(1, name);
        preparedStatement.setLong(2, idStudent);
        preparedStatement.executeUpdate();
    }

    @Override
    public void setDate(String date, long idStudent) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_DATE);
        preparedStatement.setString(1, date);
        preparedStatement.setLong(2, idStudent);
        preparedStatement.executeUpdate();
    }

    private void setStudent(long id, String name, int numberGroup, Date date, long idCurator) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_STUDENTS);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
        preparedStatement.setLong(4, idCurator);
        preparedStatement.setLong(5, id);
        preparedStatement.executeUpdate();
    }

    private void setStudent(long id, String name, int numberGroup, Date date) throws SQLException {
        preparedStatement = connection.prepareStatement(SET_STUDENTS_WITHOUT_CURATOR);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, numberGroup);
        preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException, IOException, JAXBException {  //OK
        preparedStatement = connection.prepareStatement(SELECT_STUDENTS);
        resultSet = preparedStatement.executeQuery();
        createStudents();
   //     export();
        return students;
    }

    private void createStudents() throws SQLException {
        students = new ArrayList<>();
        Student student;
        while (resultSet.next()) {
            student = new Student(resultSet.getString(INDEX_COLUMB_NAME),
                    resultSet.getInt(INDEX_COLUMB_ID_GROUP),
                    resultSet.getDate(INDEX_COLUMB_DATE),
                    resultSet.getLong(INDEX_COLUMB_ID),
                    resultSet.getLong(INDEX_COLUMB_ID_CURATOR));
            students.add(student);
        }
    }


   /* public void export(long id[]) throws JAXBException, IOException {
        Xml.writeStudents(students);
    }*/

    @Override
    public void export(String fileName, long id[]) throws JAXBException, IOException, SQLException {
        String statement = SELECT_STUDENTS;
        if (id.length == 0) {
            return;
        }
        statement += " AND STUDENTS.ID_STUDENT IN ( ? ";
        for (int i = 1; i < id.length; i++) {
            statement += " , ? ";
        }
        statement += " )";
        preparedStatement = connection.prepareStatement(statement);
        System.out.println(statement);
        for (int i = 0; i < id.length; i++) {
            preparedStatement.setLong(i + 1, id[i]);
        }
        resultSet = preparedStatement.executeQuery();
        createStudents();
        Xml.writeStudents(fileName, students);
    }

    @Override
    public void imporT(String fileName) throws JAXBException, IOException, SQLException {
        ArrayList<Student> studentsInTable = getAllStudents();
        ArrayList<Student> studentsExport = Xml.readStudents(fileName);
        ArrayList<Student> listAddWithoutCurator = new ArrayList<>();
        ArrayList<Student> listAddWithCurator = new ArrayList<>();
        ArrayList<Student> listSetWithoutCurator = new ArrayList<>();
        ArrayList<Student> listSetWithCurator = new ArrayList<>();
        Set<Long> setIdOld = new TreeSet<>();
        Set<Long> setIdNew = new TreeSet<>();
        for (int i = 0; i < studentsInTable.size(); i++) {
            setIdOld.add(studentsInTable.get(i).getID());
        }
        for (int i = 0; i < studentsExport.size(); i++) {
            setIdNew.add(studentsExport.get(i).getID());
        }
        //   setIdNew.remove(new Long(0));


        for (int i = 0; i < studentsExport.size(); i++) {
            boolean isRemoved = false;
            Student student = studentsExport.get(i);
            for (int j = 0; j < studentsInTable.size(); j++) {
                if (studentsInTable.get(j).equals(student)) { // удаляем одинаковых
                    studentsInTable.remove(j);
                    isRemoved = true;
                    break;
                }
            }
            if (!isRemoved) {                 // если удалили, то следующая итерация
                if (setIdOld.contains(student.getID())) {
                    if (setIdNew.contains(student.getID_CURATOR()) || setIdOld.contains(student.getID_CURATOR())) {
                        listSetWithCurator.add(student);
                    } else listSetWithoutCurator.add(student);
                } else {
                    if (setIdNew.contains(student.getID_CURATOR()) || setIdOld.contains(student.getID_CURATOR())) {
                        listAddWithCurator.add(student);
                    } else listAddWithoutCurator.add(student);
                }
            }
        }


        for (int i = 0; i < listAddWithCurator.size(); i++) {
            Student student = listAddWithCurator.get(i);
            insertStudent(student.getID(), student.getNAME(), student.getGROUP_STUDENT(), student.getDATE_ENROLLMENT(), student.getID_CURATOR());
        }
        for (int i = 0; i < listAddWithoutCurator.size(); i++) {
            Student student = listAddWithoutCurator.get(i);
            insertStudent(student.getID(), student.getNAME(), student.getGROUP_STUDENT(), student.getDATE_ENROLLMENT());
        }
        for (int i = 0; i < listSetWithCurator.size(); i++) {
            Student student = listSetWithCurator.get(i);
            setStudent(student.getID(), student.getNAME(), student.getGROUP_STUDENT(), student.getDATE_ENROLLMENT(), student.getID_CURATOR());
        }
        for (int i = 0; i < listSetWithoutCurator.size(); i++) {
            Student student = listSetWithoutCurator.get(i);
            setStudent(student.getID(), student.getNAME(), student.getGROUP_STUDENT(), student.getDATE_ENROLLMENT());
        }


    }

}
