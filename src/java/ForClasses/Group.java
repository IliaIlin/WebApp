package ForClasses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Group implements Serializable {

    private int groupNumber;
    private String faculty;

    private Group() {
    }

    Group(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    Group(int groupNumber, String faculty) {
        this.groupNumber = groupNumber;
        this.faculty = faculty;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

}
