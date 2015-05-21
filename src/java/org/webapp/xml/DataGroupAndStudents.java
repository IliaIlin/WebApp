package org.webapp.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import org.webapp.Group;
import org.webapp.Student;


public class DataGroupAndStudents {

    private ArrayList<Group> groups;
    private ArrayList<Student> students;

    public DataGroupAndStudents(ArrayList<Group> groups, ArrayList<Student> students) {
        this.groups = groups;
        this.students = students;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
