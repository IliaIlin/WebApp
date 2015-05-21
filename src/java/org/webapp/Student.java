package org.webapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This is a model class. It creates an object of student. The student object is connected with
 * a group object by the its number.
 * @see Group 
 * @see DataBaseStudentDaoImpl
 */
@XmlRootElement
//@XmlAccessorType(value = XmlAccessType.FIELD)
public class Student implements Serializable {

    @XmlAttribute
    private String NAME;
    @XmlAttribute
    private int GROUP_STUDENT;
    @XmlAttribute
    private Date DATE_ENROLLMENT;
    @XmlAttribute
    private long ID;
    @XmlAttribute
    private long ID_CURATOR;


    private Student() {
    }

    /**
     * 
     * @param NAME
     * @param GROUP_STUDENT
     * @param DATE_ENROLLMENT
     * @param ID
     * @param ID_CURATOR 
     */
    public Student(String NAME, int GROUP_STUDENT, Date DATE_ENROLLMENT, long ID, long ID_CURATOR) {
        this.NAME = NAME;
        this.GROUP_STUDENT = GROUP_STUDENT;
        this.DATE_ENROLLMENT = DATE_ENROLLMENT;
        this.ID = ID;
        this.ID_CURATOR = ID_CURATOR;
    }


    /**
     * This method returns a name of sudent.
     * @return String NAME
     */
    public String getNAME() {
        return NAME;
    }

    /**
     * Method returns a number of group which is binded with the student.
     * @return GROUP_STUDENT
     */
    public int getGROUP_STUDENT() {
        return GROUP_STUDENT;
    }

    /**
     * Method gets a date of enrollment of the student.
     * @return Date DATE_ENROLLMENT
     */
    public Date getDATE_ENROLLMENT() {
        return DATE_ENROLLMENT;
    }

    /**
     * Method gets an ID of the student.
     * @return long ID
     */
    public long getID() {
        return ID;
    }

    /**
     * Method returns an ID of curator of the student.
     * @return long ID_CURATOR
     */
    public long getID_CURATOR() {
        return ID_CURATOR;
    }

    /**
     * This method is a check for an input object and its parameters.  
     * @param object
     * @return True if the input object coinsides with the student's object which method is called,
     * false if the object equals Null or it (or its parameters) doesn't coinside with the first one.
     * 
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (GROUP_STUDENT != student.GROUP_STUDENT) return false;
        if (ID != student.ID) return false;
        if (ID_CURATOR != student.ID_CURATOR) return false;
        if (!NAME.equals(student.NAME)) return false;
        return DATE_ENROLLMENT.equals(student.DATE_ENROLLMENT);

    }

    /**
     * This method generates a string of student's data. Format of the string has
     * the following form:<p>
     * "Student{NAME=  'name of sudent', GROUP_STUDENT= number of student's group,
     * DATE_ENROLLMENT= date of student's enrollment, ID= student's ID, ID_CURATOR= 
     * ID of student's curator}</p>
     * @return formatted string of data
     */
    @Override
    public String toString() {
        return "Student{" +
                "NAME='" + NAME + '\'' +
                ", GROUP_STUDENT=" + GROUP_STUDENT +
                ", DATE_ENROLLMENT=" + DATE_ENROLLMENT +
                ", ID=" + ID +
                ", ID_CURATOR=" + ID_CURATOR +
                "}";
    }
}
