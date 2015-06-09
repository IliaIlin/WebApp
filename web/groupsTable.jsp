<%-- 


ment   : groupsTable
    Created on : Mar 27, 2015, 2:18:04 AM
    Author     : Илья
--%>


<%@page import="org.webapp.xml.XmlWriteRead"%>
<%@page import="org.webapp.Student"%>
<%@page import="java.io.FileWriter"%>
<%@page import="org.webapp.Group"%>
<%@page import="org.webapp.beans.WebAppBean"%>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <title>Groups Table</title>
    </head>
    <body>
        <a href=index.jsp>Main Page</a>
        <jsp:useBean id="studentBean" scope="request" class="org.webapp.beans.StudentBean" />
        <jsp:useBean id="groupBean" scope="request" class="org.webapp.beans.GroupBean" />
        <%    if (request.getParameter("GroupNo") != null) {
                ArrayList<Integer> groupNumbers = groupBean.getGroupNumbers();
                ArrayList<String> param = new ArrayList<>();
                ArrayList<String> arg = new ArrayList<>();
                if (!groupNumbers.contains(Integer.parseInt(request.getParameter("GroupNo")))) {
                    param.add("GROUP_NUMBER");
                    param.add("FACULTY");
                    arg.add(request.getParameter("GroupNo"));
                    arg.add(request.getParameter("Faculty"));
                    groupBean.editGroup(Long.parseLong(request.getParameter("ID")), param, arg);
                } else {
                    param.add("FACULTY");
                    arg.add(request.getParameter("Faculty"));
                    groupBean.editGroup(Long.parseLong(request.getParameter("ID")), param, arg);
                }
            }
            if (request.getParameter("Delete") != null) {
                ArrayList<Long> emptyIDs = groupBean.getEmptyGroupIDs();
                if (emptyIDs.contains(Long.parseLong(request.getParameter("ID")))) {
                    ArrayList<Long> id = new ArrayList<>();
                    long[] idArray = new long[]{Long.parseLong(request.getParameter("ID"))};
                    for (int i = 0; i < idArray.length; i++) {
                        id.add(idArray[i]);
                    }
                    groupBean.removeGroups(id);
                }
            }

            if (request.getParameter("groups") != null) {
                ArrayList<Long> emptyIDs = groupBean.getEmptyGroupIDs();
                String[] checkedId = request.getParameterValues("groups");
                ArrayList<Long> id = new ArrayList<>();
                if (request.getParameter("export") == null) {
                    for (int i = 0; i < checkedId.length; i++) {
                        if (emptyIDs.contains(Long.parseLong(checkedId[i]))) {
                            id.add(Long.parseLong(checkedId[i]));
                        }
                    }
                    if (id.size() != 0) {
                        groupBean.removeGroups(id);
                    }
                } else {
                    ArrayList<Student> studentsToExport = new ArrayList<>();
                    ArrayList<Group> groupsToExport = new ArrayList<>();
                    ArrayList<String> param = new ArrayList<>();
                    ArrayList<String> arg = new ArrayList<>();
                    param.add("ID_GROUP");
                    for (int i = 0; i < checkedId.length; i++) {
                        arg.add(checkedId[i]);
                        ArrayList<Group> groupsFromSelect = groupBean.getGroupsByCriterium(param, arg);
                        ArrayList<Student> studentsFromSelect = studentBean.getStudentsByCriterium(param, arg);
                        for (int j = 0; j < groupsFromSelect.size(); j++) {
                            groupsToExport.add(groupsFromSelect.get(j));
                        }
                        for (int j = 0; j < studentsFromSelect.size(); j++) {
                            studentsToExport.add(studentsFromSelect.get(j));
                        }
                        arg.clear();
                    }
                    FileWriter fw = new FileWriter("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\export.xml");
                    XmlWriteRead.writeGroupsAndStudents(groupsToExport, studentsToExport, fw);
                }
            }
        %>
        <div class="header">
            <h1>Groups Table</h1>
        </div>

        <div id="button">
            <form name="Add" action="groupAddition.jsp">
                <input type="submit" value="Add Group"/>
            </form>
            <form name="import" action="importPage.jsp" method="POST"  enctype="multipart/form-data">
                <input type="file" name="file_to_import" value="Choose_File"/>
                <input type="submit" name="import_sub" value="Import"/>
            </form>
        </div>
        <div id="centerColumnGroups">
            <form name="Table" action="groupsTable.jsp" method="GET">
                <input type="submit" action="groupsTable.jsp" value="Delete"/>
                <input type="submit" name="export" value="Export"/>
                <table border="1">
                    <thead>
                        <tr>
                            <th></th>
                            <th style="width:100px">group №</th>
                            <th style="width:100px">Faculty</th>
                            <th style="width:100px"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Group> groups = groupBean.getAllGroups();
                            for (int i = 0; i < groups.size(); i++) {
                                Group group = groups.get(i);
                        %>
                        <tr>
                            <td><input type="checkbox" name="groups" value="<%=String.valueOf(group.getID())%>"/></td>
                            <td>
                                <a href="http://localhost:8080/WebApp/groupInfo.jsp?GroupID=<%=String.valueOf(group.getID())%>&GroupNumberToShow=<%=String.valueOf(group.getGroupNumber())%>&FacultyToShow=<%=String.valueOf(group.getFaculty())%>"><%=String.valueOf(group.getGroupNumber())%>
                                </a></td>
                            <td><%=String.valueOf(group.getFaculty())%>
                            </td>
                            <td>
                                <a href="http://localhost:8080/WebApp/groupEditing.jsp?GroupID=<%=String.valueOf(group.getID())%>&GroupNumberToEdit=<%=String.valueOf(group.getGroupNumber())%>&FacultyToEdit=<%=String.valueOf(group.getFaculty())%>">
                                    <input type="button" name="Edit" value="Edit"/></a></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
            </form>
        </div>
        <% studentBean.remove();
            groupBean.remove();%>
    </body>
</html>
