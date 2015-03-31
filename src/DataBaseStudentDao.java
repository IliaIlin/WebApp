import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 03.03.2015.
 */
public interface DataBaseStudentDao {

    public void insertStudent(String name, int numberGroup, String date, int idCurator) throws SQLException;

    public void insertStudent(String name, int numberGroup, String date) throws SQLException;

    public ArrayList<Student> selectStudents(String param[], String arg[]) throws SQLException;

    public void deleteStudents(int id) throws SQLException;

    public ArrayList<Student> getAllStudents() throws SQLException;

    public void setGroup(int numberGroup, int id) throws SQLException;

    public void setCurator(int idCurator, int idStudent) throws SQLException;

}
