package classes;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 03.03.2015.
 */
public interface DataBaseStudentDao {

    public void insertStudent(String name, int numberGroup, String date, long idCurator) throws SQLException;

    public void insertStudent(String name, int numberGroup, String date) throws SQLException;

    public ArrayList<Student> selectStudents(String param[], String arg[]) throws SQLException;

    public void deleteStudents(long id[]) throws SQLException;

    public ArrayList<Student> getAllStudents() throws SQLException;

    public void setGroup(int numberGroup, long id) throws SQLException;

    public void setCurator(long idCurator, long idStudent) throws SQLException;

    public void setName(String name, long idStudent) throws SQLException;

    public void setDate(String date, long idStudent) throws SQLException;


}
