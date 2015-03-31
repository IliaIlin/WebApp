import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private final String NAME;
    private final int GROUP_STUDENT;
    private final Date DATE_ENROLLMENT;
    private final long ID;
    private final long ID_CURATOR;


    Student(String name, Date dateOfEnrollment, int groupOfStudent, long id) {
        this.NAME = name;
        this.GROUP_STUDENT = groupOfStudent;
        this.DATE_ENROLLMENT = dateOfEnrollment;
        this.ID = id;
        ID_CURATOR = -1;
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

}
