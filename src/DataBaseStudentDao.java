import java.sql.SQLException;

/**
 * Created by Саша on 03.03.2015.
 */
public interface DataBaseStudentDao {

    public void updateStudents(String s);

    public void insertStudent(String name, int numberGroup, String date,int idCurator) throws SQLException;

    public void selectStudents(String s) throws SQLException;

    public void deleteStudents(int id) throws SQLException;

    public void getAllStudents() throws SQLException;

}
