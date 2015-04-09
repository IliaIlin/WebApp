

import java.io.Serializable;


public class Group implements Serializable {

    private final int GROUP_NUMBER;
    private final String FACULTY;
    private final long ID;


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
