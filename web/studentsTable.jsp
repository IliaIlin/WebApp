<%-- 
    Document   : studentsTable
    Created on : Mar 27, 2015, 1:17:32 AM
    Author     : Илья
--%>




<%@page import="org.webapp.Group"%>
<%@page import="org.webapp.Student"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.webapp.beans.WebAppBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <title>Students Table</title>
    </head>
    
        <%

            WebAppBean bean = new WebAppBean();

            // WebAppLocal bean = (WebAppLocal) new InitialContext().lookup("java:app/WebApp/WebAppBean");
            // Properties props = new Properties();
            // props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            // InitialContext context = new InitialContext(props);
            //WebAppLocal bean = (WebAppLocal) context.lookup("java:app/WebApp/WebAppBean");
            // WebAppLocal bean=(WebAppLocal)context.lookup("WebAppLocal");
            // bean.create();

        %>     
        <a href=index.jsp>Main Page</a>
        <% if (request.getParameter("NameEditing") != null) {  // enables editing of student
                long id = Long.parseLong(request.getParameter("ID"));
                bean.editNameOfStudent(request.getParameter("NameEditing"), id);
                bean.editGroupOfStudent(Integer.parseInt(request.getParameter("GroupNumbersEditing")), id);
                bean.editDateOfStudent(request.getParameter("DateEditing"), id);
                Long curatorId = Long.parseLong(request.getParameterValues("CuratorsEditing")[0]);
                bean.editCuratorOfStudent(curatorId, id);
            }
            if (request.getParameter("Delete") != null) {     // remove checked students (got from another jsp)
                ArrayList<Long> id = new ArrayList<>();
                long[] idArray = new long[]{Long.parseLong(request.getParameter("ID"))};
                for (int i = 0; i < idArray.length; i++) {
                    id.add(idArray[i]);
                }
                bean.removeStudents(id);
            }

            if (request.getParameter("students") != null) {    // get info about checked checkboxes
                ArrayList<Long> id = new ArrayList<>();
                String[] checkedId = request.getParameterValues("students");
                for (int i = 0; i < checkedId.length; i++) {
                    id.add(Long.parseLong(checkedId[i]));
                }
                if (request.getParameter("export") == null) {     // remove checked students (got from this jsp)
                    bean.removeStudents(id);
                } else {                                         // export
                    bean.exportStudents("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\students.xml", id);
                }
            }
            if (request.getParameter("import_sub") != null) {          //import
                //   if(request.getParameter("file_to_import")!=null){
                // String fileName=request.getParameter("file_to_import");
                //  String filePath=request.getServletContext().getRealPath(fileName);
                File file;
                int maxFileSize = 5000 * 1024;
                int maxMemSize = 5000 * 1024;
                ServletContext context = pageContext.getServletContext();
                String filePath = context.getInitParameter("file_to_import");
                String contentType = request.getContentType();           
                if ((contentType.indexOf("multipart/form-data") >= 0)) {

                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    // maximum size that will be stored in memory
                    factory.setSizeThreshold(maxMemSize);
                    // Location to save data that is larger than maxMemSize.
                    factory.setRepository(new File("c:\\temp"));

                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    // maximum file size to be uploaded.
                    upload.setSizeMax(maxFileSize);
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
                             bean.importStudents(file.getAbsolutePath());%>
                            <%=filePath%>
                       <%     out.println("Uploaded Filename: " + filePath
                                    + fileName + "<br>");
                        }
                    }
                    out.println("</body>");
                    out.println("</html>");
                    //bean.importStudents("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\students.xml");
                   
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
            } 
        %>
        <div class="header">
            <h1>Students Table</h1>
        </div>
        <div id="Criteria">
            <form name="ViewCriteria" action="studentsTable.jsp" method="GET">
                Name:<input type="text" name="NameCriteria" value=""/>
                Group number:<select name="GroupNumbersCriteria">
                    <option></option>
                    <%                        ArrayList<Integer> groupsNumber = bean.getGroupNumbers();
                        for (int i = 0; i < groupsNumber.size(); i++) {%>
                    <option><%=groupsNumber.get(i)%>
                    </option>
                    <% }
                    %>
                </select>
                Date:<input type="date" name="DateCriteria"/>
                Curator:<select name="CuratorsCriteria">
                    <option></option>
                    <%
                        ArrayList<Student> students = bean.getAllStudents();
                        for (Student student : students) {%>
                    <option value="<%=student.getID()%>"><%=student.getNAME()%>
                    </option>
                    <% }
                    %>
                </select>

                <div id="button">
                    <input type="submit" value="Apply filters"/>
                    <a href="studentsTable.jsp"><input type="button" value="Clear"/></a>
                </div>
            </form>
        </div>

        <div id="button">
            <form name="Add" action="studentAddition.jsp">
                <input type="submit" value="Add Student"/>
            </form>

            <form name="import" action="studentsTable.jsp" method="POST"  enctype="multipart/form-data">
                <input type="file" name="file_to_import" value="Choose_File"/>
                <input type="submit" name="import_sub" value="Import"/>
            </form>
        </div>
        <div id="centerColumnStudents">
            <form name="studentsTable" action="studentsTable.jsp" method="POST">
                <input type="submit" value="Delete"/>
                <input type="submit" name="export" value="Export"/>
                <table border="1">
                    <thead>
                        <tr>
                            <th></th>
                            <th style="width:100px">Name</th>
                            <th style="width:100px">group №</th>
                            <th style="width:100px">Date</th>
                            <th style="width:100px">Curator</th>
                            <th style="width:100px"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            students = bean.getAllStudents();
                            String name = request.getParameter("NameCriteria");
                            String groupNumber = request.getParameter("GroupNumbersCriteria");
                            String dateInput = request.getParameter("DateCriteria");
                            String curator = request.getParameter("CuratorsCriteria");
                            ArrayList<String> param = new ArrayList<>();
                            ArrayList<String> arg = new ArrayList<>();
                            if (name != null && !name.isEmpty()) {
                                param.add("NAME");
                                arg.add(name);
                            }
                            if (groupNumber != null && !groupNumber.isEmpty()) {
                                param.add("GROUP_NUMBER");
                                arg.add(groupNumber);
                            }
                            if (dateInput != null && !dateInput.isEmpty()) {
                                param.add("DATE");
                                arg.add(dateInput);
                            }
                            if (curator != null && !curator.isEmpty()) {
                                param.add("CURATOR");
                                if (curator == "") {
                                    arg.add("0");
                                } else {
                                    arg.add(curator);
                                }
                            }
                            students = bean.getStudentsByCriterium(param, arg);
                            ArrayList<Student> studentsFull = bean.getAllStudents();
                            for (int i = 0; i < students.size(); i++) {
                                Student student = students.get(i);
                                param.clear();
                                arg.clear();
                                param.add("GROUP_NUMBER");
                                arg.add(String.valueOf(student.getGROUP_STUDENT()));
                                ArrayList<Group> groupToRedirect = bean.getGroupsByCriterium(param, arg);
                                Group group = groupToRedirect.get(0);

                        %>
                        <tr>
                            <td><input type="checkbox" name="students" value="<%=student.getID()%>"</td>
                            <td>
                                <a href="http://localhost:8080/WebApp/studentInfo.jsp?StudentID=<%=String.valueOf(student.getID())%>&StudentNameToShow=<%=student.getNAME()%>&StudentGroupToShow=<%=String.valueOf(student.getGROUP_STUDENT())%>&StudentDateToShow=<%=String.valueOf(student.getDATE_ENROLLMENT())%>&StudentCuratorToShow=<%=student.getID_CURATOR()%>"><%=String.valueOf(student.getNAME())%>
                                </a></td>
                            <td>
                                <a href="http://localhost:8080/WebApp/groupInfo.jsp?GroupID=<%=String.valueOf(group.getID())%>&GroupNumberToShow=<%=String.valueOf(group.getGroupNumber())%>&FacultyToShow=<%=String.valueOf(group.getFaculty())%>"><%=String.valueOf(student.getGROUP_STUDENT())%>
                                </a></td>
                            <td><%=String.valueOf(student.getDATE_ENROLLMENT())%>
                            </td>
                            <% if (Integer.parseInt(String.valueOf(student.getID_CURATOR())) == 0) {%>
                            <td><%=""%>
                            </td>
                            <% } else {%>
                            <td><% long id = student.getID_CURATOR();
                                A:
                                for (int j = 0; j < studentsFull.size(); j++) {
                                    if (studentsFull.get(j).getID() == id) {%>
                                <%=studentsFull.get(j).getNAME()%>
                                <% break A;
                                    }
                                }
                                %>
                            </td>


                            <%
                                }%>
                            <td>
                                <a href="http://localhost:8080/WebApp/studentEditing.jsp?StudentID=<%=String.valueOf(student.getID())%>&StudentNameToEdit=<%=student.getNAME()%>&StudentGroupToEdit=<%=String.valueOf(student.getGROUP_STUDENT())%>&StudentDateToEdit=<%=String.valueOf(student.getDATE_ENROLLMENT())%>&StudentCuratorToEdit=<%=student.getID_CURATOR()%>">
                                    <input type="button" name="Edit" value="Edit"/></a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </form>
        </div>
        <%bean.remove();%>
    
</html>
