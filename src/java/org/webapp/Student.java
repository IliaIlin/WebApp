package org.webapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;


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

    public Student(String NAME, int GROUP_STUDENT, Date DATE_ENROLLMENT, long ID, long ID_CURATOR) {
        this.NAME = NAME;
        this.GROUP_STUDENT = GROUP_STUDENT;
        this.DATE_ENROLLMENT = DATE_ENROLLMENT;
        this.ID = ID;
        this.ID_CURATOR = ID_CURATOR;
    }

    public String getNAME() {
        return NAME;
    }

    public int getGROUP_STUDENT() {
        return GROUP_STUDENT;
    }

    public Date getDATE_ENROLLMENT() {
        return DATE_ENROLLMENT;
    }

    public long getID() {
        return ID;
    }

    public long getID_CURATOR() {
        return ID_CURATOR;
    }

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