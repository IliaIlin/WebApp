package classes;

import javax.xml.bind.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by ���� on 16-Apr-15.
 */
public class Xml {

    private static String fileNameForStudents = "students.xml";
    private static String fileNameForGroups = "groups.xml";
    private static FileOutputStream outputStream;
    private static FileInputStream inputStream;


    public static void writeGroups(ArrayList groups) throws JAXBException, IOException {
        DataGroups data = new DataGroups(groups);
        outputStream = new FileOutputStream(fileNameForGroups);
        write(data, DataGroups.class);
    }

    public static void writeGroups(String fileName, ArrayList groups) throws JAXBException, IOException {
        DataGroups data = new DataGroups(groups);
        outputStream = new FileOutputStream(fileName);
        write(data, DataGroups.class);
    }


    public static void writeStudents(ArrayList students) throws JAXBException, IOException {
        DataStudents data = new DataStudents(students);
        outputStream = new FileOutputStream(fileNameForStudents);
        write(data, DataStudents.class);
    }


    public static void writeStudents(String fileName, ArrayList students) throws JAXBException, IOException {
        DataStudents data = new DataStudents(students);
        outputStream = new FileOutputStream(fileName);
        write(data, DataStudents.class);
    }

    private static void write(Data data, Class aClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, outputStream);
    }


    public static ArrayList<Group> readGroups(String fileName) throws JAXBException, IOException {
        inputStream = new FileInputStream(fileName);
        return ((DataGroups) read(DataGroups.class)).getObjects();
    }


    public static ArrayList<Student> readStudents(String fileName) throws JAXBException, IOException {
        inputStream = new FileInputStream(fileName);
        return ((DataStudents) read(DataStudents.class)).getObjects();
    }

 /*   private static ArrayList read(Class aClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Data data = (Data) unmarshaller.unmarshal(inputStream);
        return data.getObjects();
    }
*/
    private static Object read(Class aClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object data = unmarshaller.unmarshal(inputStream);
        return data;
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
