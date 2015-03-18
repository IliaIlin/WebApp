
import java.sql.SQLException;

/**
 * Created by Саша on 03.03.2015.
 */
public interface DataBaseDao {

    public void updateStudents(String s);

    public void updateGroups(String s);

    public void insertStudent(String name, int numberGroup, String date) throws SQLException;

    public void insertGroup(int numberGroup, String faculty) throws SQLException;

    public void deleteGroups(int numberGroup) throws SQLException;

    public void deleteStudents(int id) throws SQLException;

    public void selectGroups(String s);

    public void selectStudents(String s);

    public void getAllStudents() throws SQLException;

    public void getAllGroups() throws SQLException;
}
