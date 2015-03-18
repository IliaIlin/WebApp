
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Student implements Serializable {

    private String name;
    private Group groupOfStudent;
    private Date dateOfEnrollment;
    public final long ID;

    private Student() {  //без этого конструктора не создается xml
        this.ID = -1;
    }

    Student(String name, Date dateOfEnrollment, Group groupOfStudent) {
        this.name = name;
        this.groupOfStudent = groupOfStudent;
        this.dateOfEnrollment = dateOfEnrollment;
        this.ID = idGenerator();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroupOfStudent() {
        return groupOfStudent;
    }

    public void setGroupOfStudent(Group groupOfStudent) {
        this.groupOfStudent = groupOfStudent;
    }

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date date) {
        this.dateOfEnrollment = date;
    }

    public long getIdNumber() {
        return ID;
    }


    private long idGenerator() {
        long result = name != null ? name.hashCode() : 0;
        result = 31 * result + (groupOfStudent != null ? groupOfStudent.hashCode() : 0);
        result = 31 * result + (dateOfEnrollment != null ? dateOfEnrollment.hashCode() : 0);
        result = 31 * result + (System.nanoTime() ^ (System.nanoTime() >>> 32));
        return Math.abs(result);
    }
}
