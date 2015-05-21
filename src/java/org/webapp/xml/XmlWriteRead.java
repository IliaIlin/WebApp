package org.webapp.xml;

import org.webapp.Group;
import org.webapp.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

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


    public static void writeGroupsAndStudents(ArrayList<Group> groups, ArrayList<Student> students,String file) throws XMLStreamException, IOException {
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(
                new FileWriter(file));

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
                    Date d = student.getDATE_ENROLLMENT();
                    String date = d.getDay() + "." + d.getMonth() + "." + d.getYear();
                    writer.writeAttribute("date", date);
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

    public static void test() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(new FileReader("output2.xml"));
        ArrayList<Group> groups = new ArrayList<>();
        try {
            int event = r.getEventType();
            while (true) {
                switch (event) {
                    case XMLStreamConstants.START_DOCUMENT:
                        System.out.println("Start Document.");
                        break;
                    case XMLStreamConstants.START_ELEMENT:
                        System.out.println("Start Element: " + r.getName());
                        for (int i = 0, n = r.getAttributeCount(); i < n; ++i) {
                            System.out.println("Attribute: " + r.getAttributeName(i)
                                    + "=" + r.getAttributeValue(i));
                        }
                        if ("group".equals(r.getName().toString())) {
                            groups.add(new Group(new Integer(r.getAttributeValue(0)), r.getAttributeValue(1),
                                    new Long(r.getAttributeValue(2))));
                        }

                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if (r.isWhiteSpace())
                            break;

                        System.out.println("Text: " + r.getText());
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        System.out.println("End Element:" + r.getName());
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        System.out.println("End Document.");
                        break;
                }

                if (!r.hasNext())
                    break;

                event = r.next();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            for (int i = 0; i < groups.size(); i++) {
                System.out.println(groups.get(i).toString());
            }
            r.close();
        }
    }

    public static DataGroupAndStudents readGroupAndStudents(String file) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(new FileReader(file));
        ArrayList<Group> groups = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        try {
            int event = r.getEventType();
            while (true) {
                System.out.println(1);
                if (event == XMLStreamConstants.START_ELEMENT && "group".equals(r.getName().toString())) {
                    groups.add(new Group(new Integer(r.getAttributeValue(0)), r.getAttributeValue(1),
                            new Long(r.getAttributeValue(2))));
                    while (true) {
                        System.out.println(2);
                        boolean isExit = false;
                        boolean isStart = false;
                        String name = "";
                        String date = "";
                        long id = 0;
                        long idCurator = 0;
                        switch (event) {
                            case XMLStreamConstants.START_ELEMENT:
                                if ("student".equals(r.getName().toString())) {
                                    isStart = true;
                                    name = r.getAttributeValue(0);
                                    date = r.getAttributeValue(1);
                                    id = new Long(r.getAttributeValue(2));
                                }
                                if ("curator".equals(r.getName().toString())) {
                                    idCurator = new Long(r.getAttributeValue(2));
                                }
                                break;
                            case XMLStreamConstants.END_ELEMENT:
                                if (isStart) {
                                    String s[] = date.split(".");
                                    students.add(new Student(name, groups.get(groups.size() - 1).getGroupNumber(),
                                            new Date(new Integer(s[0]), new Integer(s[1]), new Integer(s[2])), id, idCurator));
                                }
                                isExit = true;
                                break;
                        }
                        if (isExit)
                            break;
                        event = r.next();
                    }
                }
                if (event == XMLStreamConstants.END_DOCUMENT) {
                    break;
                }
                event = r.next();

            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            for (int i = 0; i < groups.size(); i++) {
                System.out.println(groups.get(i).toString());
            }
            r.close();
        }
        return new DataGroupAndStudents(groups, students);
    }

}
