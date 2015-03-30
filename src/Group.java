import java.io.Serializable;


public class Group implements Serializable {

    private int GROUP_NUMBER;
    private String FACULTY;

    private Group() {
    }

    Group(int groupNumber) {
        this.GROUP_NUMBER = groupNumber;
    }

    Group(int groupNumber, String faculty) {
        this.GROUP_NUMBER = groupNumber;
        this.FACULTY = faculty;
    }

    public int getGROUP_NUMBER() {
        return GROUP_NUMBER;
    }

    public String getFACULTY() {
        return FACULTY == null ? "" : FACULTY;
    }


}
