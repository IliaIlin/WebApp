package org.webapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

    /**
     * This is a model class. It creates an object of group. 
     * @see DataBaseGroupDaoImpl
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
     * @return groupNumber
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Method returns the faculty of the group.
     * @return faculty
     */
    public String getFaculty() {
        return faculty == null ? "" : faculty;
    }

    /**
     * Method returns ID of the group.
     * @return ID
     */
    public long getID() {
        return ID;
    }

    /**
     * This method is a check for an input object and its parameters.  
     * @param object
     * @return True if the input object coinsides with the group which method is called,
     * false if the object equals Null or it (or its parameters) doesn't coinside with the first one.
     * 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (groupNumber != group.groupNumber) return false;
        if (ID != group.ID) return false;
        return faculty.equals(group.faculty);

    }

    /**
     * This method generates a string of group's data. Format of the string has
     * the following form:<p>
     * "Group{groupNumber=  number of the group, faculty= faculty of the group,
     * ID= group's ID}"</p>
     * @return formatted string of data
     */
    @Override
    public String toString() {
        return "Group{" +
                "groupNumber=" + groupNumber +
                ", faculty='" + faculty + '\'' +
                ", ID=" + ID +
                '}';
    }
}
