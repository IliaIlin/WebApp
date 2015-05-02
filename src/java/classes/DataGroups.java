package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by ���� on 15-Apr-15.
 */

@XmlRootElement
public class DataGroups implements Data {

    @XmlElement
    private ArrayList<Group> groups;

    private DataGroups() {
    }

    public DataGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Group> getObjects() {
        return groups;
    }

}