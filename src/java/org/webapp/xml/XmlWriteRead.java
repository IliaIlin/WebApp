package org.webapp.xml;

import org.webapp.Group;
import org.webapp.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by ���� on 16-Apr-15.
 */
public class XmlWriteRead {

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

    public static void writeGroupAndStudents(String fileName, ArrayList<Group> groups,
                                             ArrayList<Student> students) throws JAXBException, IOException {
        DataGroupAndStudents data = new DataGroupAndStudents(groups, students);
        outputStream = new FileOutputStream(fileName);
        write(data, DataGroupAndStudents.class);
    }

    private static void write(Data data, Class aClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, outputStream);
    }


    public static ArrayList<Group> readGroups(String fileName) throws JAXBException, IOException {
        inputStream = new FileInputStream(fileName);
        return ((DataGroups) read(DataGroups.class)).getGroups();
    }


    public static ArrayList<Student> readStudents(String fileName) throws JAXBException, IOException {
        inputStream = new FileInputStream(fileName);
        return ((DataStudents) read(DataStudents.class)).getStudents();
    }

    public static DataGroupAndStudents readGroupAndStudents(String fileName) throws JAXBException, IOException {
        inputStream = new FileInputStream(fileName);
        return (DataGroupAndStudents) read(DataGroupAndStudents.class);
    }

    /*   private static ArrayList read(Class aClass) throws JAXBException {
           JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
           Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
           Data data = (Data) unmarshaller.unmarshal(inputStream);
           return data.getStudents();
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
            XmlWriteRead.fileNameForStudents = FILE_NAME_FOR_STUDENTS;
    }

    public static void setFILE_NAME_FOR_GROUPS(String FILE_NAME_FOR_GROUPS) {
        if (FILE_NAME_FOR_GROUPS != null && !FILE_NAME_FOR_GROUPS.equals(fileNameForStudents))
            XmlWriteRead.fileNameForGroups = FILE_NAME_FOR_GROUPS;
    }


    public static void writeGroupsAndStudents(ArrayList<Group> groups, ArrayList<Student> students) throws JAXBException, XMLStreamException, IOException {
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(
                new FileWriter("output2.xml"));

        writer.writeStartDocument();
        writer.writeStartElement("groups");
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            writer.writeStartElement("group");
            writer.writeAttribute("groupNumber", Integer.toString(group.getGroupNumber()));
            writer.writeAttribute("faculty", group.getFaculty());
            writer.writeAttribute("id", Long.toString(group.getID()));
            writer.writeStartElement("students");
            for (int j = 0; j < students.size(); j++) {
                Student student = students.get(j);
                if (student.getGROUP_STUDENT() == group.getGroupNumber()) {
                    writer.writeStartElement("student");
                    writer.writeAttribute("name", student.getNAME());
                    writer.writeAttribute("date", student.getDATE_ENROLLMENT().toString());
                    writer.writeAttribute("id", Long.toString(student.getID()));
                    if (student.getID_CURATOR() != 0) {
                        for (int k = 0; k < students.size(); k++) {
                            if (students.get(k).getID() == student.getID_CURATOR()) {
                                student = students.get(k);
                                break;
                            }
                        }
                        writer.writeStartElement("curator");
                        writer.writeAttribute("name", student.getNAME());
                        writer.writeAttribute("date", student.getDATE_ENROLLMENT().toString());
                        writer.writeAttribute("id", Long.toString(student.getID()));
                        writer.writeAttribute("groupNumber", Integer.toString(student.getGROUP_STUDENT()));
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                }
            }
            writer.writeEndElement();
            writer.writeEndElement();
        }

        writer.writeEndDocument();

        writer.flush();
        writer.close();


    }
}
