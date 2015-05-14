package org.webapp.xml;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import org.webapp.Group;
import org.webapp.Student;



@XmlRootElement(name = "group")
public class GroupAndStudents {

    @XmlElement
    private Group group;
    @XmlElement(name = "student")
    private ArrayList<Student> students;

    private GroupAndStudents() {
    }

    public GroupAndStudents(Group group, ArrayList<Student> students) {
        this.group = group;
        this.students = students;
    }

    public Group getGroup() {
        return group;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "GroupAndStudents{" +
                "group=" + group +
                ", students=" + students +
                '}';
    }
}
