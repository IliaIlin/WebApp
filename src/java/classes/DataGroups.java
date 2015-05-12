package classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

/**
 * Created by ���� on 15-Apr-15.
 */

@XmlRootElement(name = "groups")
public class DataGroups implements Data{

    @XmlElement(name = "group")
    private ArrayList<Group> groups;

    private DataGroups() {
    }

    public DataGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

}