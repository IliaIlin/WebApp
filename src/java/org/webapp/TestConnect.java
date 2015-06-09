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
        DataSource dataSource = new DataSource("root", "root");
        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());

        String[] checkedId = {"121", "112"};
        ArrayList<Student> studentsToExport = new ArrayList<>();
                    ArrayList<Group> groupsToExport = new ArrayList<>();
                    ArrayList<String> param = new ArrayList<>();
                    ArrayList<String> arg = new ArrayList<>();
                    for (int i = 0; i < checkedId.length; i++) {
                        param.add("ID_STUDENT");
                        arg.add(checkedId[i]);
                        ArrayList<Student> studentsFromSelect = dataBaseStudentDao.selectStudents(param, arg);
                        System.out.println(studentsFromSelect.toString());
                        param.clear();
                        arg.clear();
                        studentsToExport.add(studentsFromSelect.get(0));
                        int groupNumber = studentsFromSelect.get(0).getGROUP_STUDENT();
                        param.add("GROUP_NUMBER");
                        arg.add(String.valueOf(groupNumber));
                        ArrayList<Group> groupsFromSelect = dataBaseGroupDao.selectGroups(param, arg);
                        param.clear();
                        arg.clear();
                        //if(groupsToExport.isEmpty()){
                       //    groupsToExport.add(groupsFromSelect.get(0)); 
                       // }
                      //  else if(!groupsToExport.contains(groupsFromSelect.get(0))){
                        groupsToExport.add(groupsFromSelect.get(0));
                       // }
                    }
                    System.out.println(studentsToExport.toString());
                    System.out.println(groupsToExport.toString());
                    FileWriter fw = new FileWriter("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\export.xml");
                    //studentBean.exportStudents(fw, id);
                    XmlWriteRead.writeGroupsAndStudents(groupsToExport, studentsToExport, fw);

    }


}
