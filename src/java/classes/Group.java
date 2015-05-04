package classes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Group implements Serializable {

    private int groupNumber;
    private String faculty;
    private long ID;

    private Group() {
    }

    public Group(int groupNumber, String faculty, long ID) {
        this.groupNumber = groupNumber;
        this.faculty = faculty;
        this.ID = ID;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public String getFaculty() {
        return faculty == null ? "" : faculty;
    }

    public long getID() {
        return ID;
    }


    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupNumber != group.groupNumber) return false;
        if (ID != group.ID) return false;
        return faculty.equals(group.faculty);

    }


    @Override
    public String toString() {
        return "Group{" +
                "groupNumber=" + groupNumber +
                ", faculty='" + faculty + '\'' +
                ", ID=" + ID +
                '}';
    }
}
