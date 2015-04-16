package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by ���� on 15-Apr-15.
 */

@XmlRootElement
public class Data {

    @XmlElement
    private ArrayList<Group> groups;
    @XmlElement
    private ArrayList<Student> students;

    private boolean isStudents = false;

    private Data() {
    }

    public Data(ArrayList objects) {
        if (objects.size() == 0) return;

        if (objects.get(0).getClass().getSimpleName().equals(Group.class.getSimpleName())) {
            groups = objects;
        } else {
            students = objects;
            isStudents = true;
        }
    }

    public boolean isStudents(){
        return isStudents;
    }
}