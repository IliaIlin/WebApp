import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Group implements Serializable {

    private  int GROUP_NUMBER;
    private  String FACULTY;
    private  long ID;

    private Group() {
    }

    public Group(int GROUP_NUMBER, String FACULTY, long ID) {
        this.GROUP_NUMBER = GROUP_NUMBER;
        this.FACULTY = FACULTY;
        this.ID = ID;
    }

    public int getGROUP_NUMBER() {
        return GROUP_NUMBER;
    }

    public String getFACULTY() {
        return FACULTY == null ? "" : FACULTY;
    }

    public long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Group{" +
                "GROUP_NUMBER=" + GROUP_NUMBER +
                ", FACULTY='" + FACULTY + '\'' +
                ", ID=" + ID +
                '}';
    }
}
