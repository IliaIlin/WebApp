package org.webapp.xml;

import org.webapp.Group;
import org.webapp.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ���� on 16-Apr-15.
 */
public class XmlWriteRead {

    private static FileWriter outputStream;
    private static FileReader inputStream;


    public static void writeGroups(FileWriter fileWriter, ArrayList groups) throws JAXBException, IOException {
        DataGroups data = new DataGroups(groups);
        outputStream = fileWriter;
        write(data, DataGroups.class);
    }


    public static void writeStudents(FileWriter fileWriter, ArrayList students) throws JAXBException, IOException {
        DataStudents data = new DataStudents(students);
        outputStream = fileWriter;
        write(data, DataStudents.class);
    }


    private static void write(Data data, Class aClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, outputStream);
    }


    public static ArrayList<Group> readGroups(FileReader fileReader) throws JAXBException, IOException {
        inputStream = fileReader;
        return ((DataGroups) read(DataGroups.class)).getGroups();
    }


    public static ArrayList<Student> readStudents(FileReader fileReader) throws JAXBException, IOException {
        inputStream = fileReader;
        return ((DataStudents) read(DataStudents.class)).getStudents();
    }


    private static Object read(Class aClass) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(aClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Object data = unmarshaller.unmarshal(inputStream);
        return data;
    }


    public static void writeGroupsAndStudents(ArrayList<Group> groups, ArrayList<Student> students, FileWriter fileWriter) throws XMLStreamException, IOException {
        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(fileWriter);


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
                    String date = new SimpleDateFormat("dd.MM.YY").format(student.getDATE_ENROLLMENT());
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


    public static DataGroupAndStudents readGroupAndStudents(FileReader fileReader) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader r = factory.createXMLStreamReader(fileReader);
        ArrayList<Group> groups = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        try {
            int event = r.getEventType();
            while (true) {
                if (event == XMLStreamConstants.START_ELEMENT && "group".equals(r.getName().toString())) {
                    groups.add(new Group(new Integer(r.getAttributeValue(0)), r.getAttributeValue(1),
                            new Long(r.getAttributeValue(2))));
                    boolean isExit = false;
                    String name = "";
                    String date = "";
                    long id = 0;
                    long idCurator = 0;
                    while (true) {
                        switch (event) {
                            case XMLStreamConstants.START_ELEMENT:
                                if ("student".equals(r.getName().toString())) {
                                    name = r.getAttributeValue(0);
                                    date = r.getAttributeValue(1);
                                    id = new Long(r.getAttributeValue(2));
                                }
                                if ("curator".equals(r.getName().toString())) {
                                    idCurator = new Long(r.getAttributeValue(1));
                                }
                                break;
                            case XMLStreamConstants.END_ELEMENT:
                                if ("student".equals(r.getName().toString())) {
                                    String string = "\\.";
                                    String s[] = date.split(string);
                                    students.add(new Student(name, groups.get(groups.size() - 1).getGroupNumber(),
                                            new Date(new Integer(s[0]), new Integer(s[1]), new Integer(s[2])), id, idCurator));
                                }
                                if ("group".equals(r.getName().toString())) {
                                    isExit = true;
                                }
                                break;
                        }
                        if (isExit) break;
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
            r.close();
        }
        return new DataGroupAndStudents(groups, students);
    }

}
