package org.webapp;

import org.webapp.xml.XmlWriteRead;

import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Саша on 18.03.2015.
 *
 * @deprecated
 */
public class TestConnect {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException, JAXBException, NamingException, XMLStreamException {
        //DataSourcePool dataSource = new DataSourcePool();
        DataSource dataSource = new DataSource("root", "11");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());

        String[] checkedId = {"8", "10"};
        ArrayList<Student> studentsToExport = new ArrayList<>();
        ArrayList<Group> groupsToExport = new ArrayList<>();
        ArrayList<String> param = new ArrayList<>();
        ArrayList<String> arg = new ArrayList<>();
//long[] idArray = new long[checkedId.length];
        for (int i = 0; i < checkedId.length; i++) {
// id.add(Long.parseLong(checkedId[i]));
            param.add("ID_GROUP");
            arg.add(checkedId[i]);
            ArrayList<Group> groupsFromSelect = dataBaseGroupDao.selectGroups(param, arg);
// param.clear();
// param.add("ID_GROUP");
            ArrayList<Student> studentsFromSelect = dataBaseStudentDao.selectStudents(param, arg);
            for (int j = 0; j < groupsFromSelect.size(); j++) {
                groupsToExport.add(groupsFromSelect.get(j));
            }
            for (int j = 0; j < studentsFromSelect.size(); j++) {
                studentsToExport.add(studentsFromSelect.get(j));
            }
// param.clear();
            arg.clear();
        }
        FileWriter fw = new FileWriter("export.xml");
//groupBean.exportGroups(fw, id);
        XmlWriteRead.writeGroupsAndStudents(groupsToExport, studentsToExport, fw);


    }


}
