import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Саша on 16-Apr-15.
 */
public class Xml {

    private static String fileNameForStudents = "students.xml";
    private static String fileNameForGroups = "groups.xml";
    private static FileOutputStream stream;



    public static void write(ArrayList list) throws JAXBException, IOException {

        Data data = new Data(list);
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        if (data.isStudents()) {
            stream = new FileOutputStream(fileNameForStudents);
        } else {
            stream = new FileOutputStream(fileNameForGroups);
        }
        marshaller.marshal(data, stream);

    }

    public static void setFILE_NAME_FOR_STUDENTS(String FILE_NAME_FOR_STUDENTS) {
        if (FILE_NAME_FOR_STUDENTS != null && !FILE_NAME_FOR_STUDENTS.equals(fileNameForGroups))
            Xml.fileNameForStudents = FILE_NAME_FOR_STUDENTS;
    }

    public static void setFILE_NAME_FOR_GROUPS(String FILE_NAME_FOR_GROUPS) {
        if (FILE_NAME_FOR_GROUPS != null && !FILE_NAME_FOR_GROUPS.equals(fileNameForStudents))
            Xml.fileNameForGroups = FILE_NAME_FOR_GROUPS;
    }
}
