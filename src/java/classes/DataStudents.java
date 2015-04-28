package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by ���� on 15-Apr-15.
 */

@XmlRootElement
public class DataStudents implements Data {

    @XmlElement
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