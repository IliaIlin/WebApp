package java.ForClasses;

import java.sql.SQLException;

/**
 * Created by Саша on 26.03.2015.
 */
public interface DataBaseGroupDao  {

    public void insertGroup(int numberGroup, String faculty) throws SQLException;

    public void deleteGroups(int numberGroup) throws SQLException;

    public void updateGroups(String s);

    public void selectGroups(String s) throws SQLException;

    public void getAllGroups() throws SQLException;

}
