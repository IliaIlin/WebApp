package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * Created by ���� on 15-Apr-15.
 */

@XmlRootElement(name = "students")
public class DataStudents implements Data {

    @XmlElement(name = "student")
    private ArrayList<Student> students;

    
    private DataStudents() {
    }

    public DataStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Student> getObjects() {
        return students;
    }
}