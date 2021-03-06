package org.webapp.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import org.webapp.Student;

/**
 * Created by ���� on 15-Apr-15.
 */

@XmlRootElement(name = "students")
public class DataStudents implements Data{

    @XmlElement(name = "student")
    private ArrayList<Student> students;

    
    private DataStudents() {
    }

    public DataStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}