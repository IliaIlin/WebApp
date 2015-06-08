<%-- 
    Document   : importPage
    Created on : May 26, 2015, 7:20:14 PM
    Author     : Илья
--%>

<%@page import="org.webapp.Student"%>
<%@page import="org.webapp.Student"%>
<%@page import="org.webapp.Group"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.webapp.xml.DataGroupAndStudents"%>
<%@page import="org.webapp.xml.XmlWriteRead"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <title>Import Page</title>
    </head>
    <body>
        <jsp:useBean id="studentBean" scope="request" class="org.webapp.beans.StudentBean" />
        <jsp:useBean id="groupBean" scope="request" class="org.webapp.beans.GroupBean" />
        <%
            File file;
            int maxFileSize = 5000 * 1024;
            int maxMemSize = 5000 * 1024;
            ServletContext context = pageContext.getServletContext();
            String filePath = context.getInitParameter("file-upload");

            // Verify the content type
            String contentType = request.getContentType();
            if ((contentType.indexOf("multipart/form-data") >= 0)) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // maximum size that will be stored in memory
                factory.setSizeThreshold(maxMemSize);
                // Location to save data that is larger than maxMemSize.
                factory.setRepository(new File("C:\\"));

                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // maximum file size to be uploaded.
                upload.setSizeMax(maxFileSize);
                try {
                    // Parse the request to get file items.
                    List fileItems = upload.parseRequest(request);

                    // Process the uploaded file items
                    Iterator i = fileItems.iterator();

                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>JSP File upload</title>");
                    out.println("</head>");
                    out.println("<body>");
                    while (i.hasNext()) {
                        FileItem fi = (FileItem) i.next();
                        if (!fi.isFormField()) {
                            // Get the uploaded file parameters
                            String fieldName = fi.getFieldName();
                            String fileName = fi.getName();
                            boolean isInMemory = fi.isInMemory();
                            long sizeInBytes = fi.getSize();
                            // Write the file
                            if (fileName.lastIndexOf("\\") >= 0) {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\")));
                            } else {
                                file = new File(filePath
                                        + fileName.substring(fileName.lastIndexOf("\\") + 1));
                            }
                            fi.write(file);
                            FileReader fr = new FileReader(filePath + fileName);
                            DataGroupAndStudents d = XmlWriteRead.readGroupAndStudents(fr);
                            ArrayList<Group> groupsNew = d.getGroups();
                            ArrayList<Student> studentsNew = d.getStudents();
                            groupBean.importGroups(groupsNew);
                            studentBean.importStudents(studentsNew); %>
        <h2 class="header">Successful Upload! </h2>
        <a href="studentsTable.jsp">Students Table</a>
        <br>
        <br>
        <a href="groupsTable.jsp">Groups Table</a>
        <%
                        }
                    }
                    out.println("</body>");
                    out.println("</html>");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet upload</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<p>No file uploaded</p>");
                out.println("</body>");
                out.println("</html>");
            }
            studentBean.remove();
            groupBean.remove();
        %>
    </body>
</html>
