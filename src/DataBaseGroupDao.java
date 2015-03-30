import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 26.03.2015.
 */
public interface DataBaseGroupDao  {

    public ResultSet insertGroup(int numberGroup, String faculty) throws SQLException;

    public void deleteGroups(int numberGroup) throws SQLException;

    public void updateGroups(int numberGroup, String faculty) throws SQLException;

    public ArrayList<Group> selectGroups(String param[], String arg[]) throws SQLException;

    public ArrayList<Group> getAllGroups() throws SQLException;


}
