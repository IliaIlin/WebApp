package org.webapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

    /**
     * This is a model class. It creates an object of group. 
     */
@XmlRootElement
//@XmlAccessorType(value = XmlAccessType.FIELD)
public class Group implements Serializable {

    @XmlAttribute
    private int groupNumber;
    @XmlAttribute
    private String faculty;
    @XmlAttribute
    private long ID;
    
    private Group() {
    }

    /**
     * 
     * @param groupNumber
     * @param faculty
     * @param ID 
     */
    public Group(int groupNumber, String faculty, long ID) {
        this.groupNumber = groupNumber;
        this.faculty = faculty;
        this.ID = ID;
    }

    /**
     * This method returns the number of group.
     * @return int groupNumber
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Method returns the faculty of the group.
     * @return Sting faculty
     */
    public String getFaculty() {
        return faculty == null ? "" : faculty;
    }

    /**
     * Method returns ID of the group.
     * @return long ID
     */
    public long getID() {
        return ID;
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
