package org.webapp.xml;

import org.webapp.xml.GroupAndStudents;
import org.webapp.xml.Data;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import org.webapp.Group;
import org.webapp.Student;


@XmlRootElement(name = "groups")
public class DataGroupAndStudents implements Data {

    @XmlElement(name = "group")
    private ArrayList<GroupAndStudents> groupAndStudentses;

    private DataGroupAndStudents() {
    }


    public DataGroupAndStudents(ArrayList<Group> groups, ArrayList<Student> students) {
        groupAndStudentses = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            int numberGroup = group.getGroupNumber();
            ArrayList<Student> students1 = new ArrayList<>();
            for (int j = 0; j < students.size(); j++) {
                Student student = students.get(j);
                if (student.getGROUP_STUDENT() == numberGroup) {
                    students1.add(student);
                }
            }
            groupAndStudentses.add(new GroupAndStudents(group, students1));
        }
    }

    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        for (int i = 0; i < groupAndStudentses.size(); i++) {
            groups.add(groupAndStudentses.get(i).getGroup());
        }
        return groups;
    }

    public ArrayList<Student> getStudents(){
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < groupAndStudentses.size(); i++) {
            ArrayList<Student> students1 = groupAndStudentses.get(i).getStudents();
            for (int j = 0; j <students1.size(); j++) {
                students.add(students1.get(j));
            }
        }
        return students;
    }
}
