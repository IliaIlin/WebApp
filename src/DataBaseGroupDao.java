import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Саша on 26.03.2015.
 */
public interface DataBaseGroupDao  {

    public ResultSet insertGroup(int numberGroup, String faculty) throws SQLException;

    public void deleteGroups(int numberGroup) throws SQLException;

    public void updateGroups(int numberGroup, String faculty) throws SQLException;

    public void selectGroups(String s) throws SQLException;

    public ResultSet getAllGroups() throws SQLException;

}
