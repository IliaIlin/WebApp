import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Саша on 03.03.2015.
 */
public interface DataBaseStudentDao {

    public void updateStudents(String s);

    public ResultSet insertStudent(String name, int numberGroup, String date, int idCurator) throws SQLException;

    public ResultSet selectStudents(String s) throws SQLException;

    public void deleteStudents(int id) throws SQLException;

    public ResultSet getAllStudents() throws SQLException;

    public void setGroup(int id, int numberGroup) throws SQLException;

    public void setCurator(int idStudent, int idCurator) throws SQLException;

}
