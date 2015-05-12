<%-- 
    Document   : studentsTable
    Created on : Mar 27, 2015, 1:17:32 AM
    Author     : Илья
--%>

<%@page import="classes.Group" %>
<%@page import="classes.Student" %>
<%@page import="classes.WebAppBean" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/Style.css">
    <title>Students Table</title>
</head>
<body>
<%WebAppBean bean = new WebAppBean();%>
<a href=index.jsp>Main Page</a>
<% if (request.getParameter("NameEditing") != null) {
    long id = Long.parseLong(request.getParameter("ID"));
    bean.editNameOfStudent(request.getParameter("NameEditing"), id);
    bean.editGroupOfStudent(Integer.parseInt(request.getParameter("GroupNumbersEditing")), id);
    bean.editDateOfStudent(request.getParameter("DateEditing"), id);
    Long curatorId = Long.parseLong(request.getParameterValues("CuratorsEditing")[0]);
    bean.editCuratorOfStudent(curatorId, id);
}
    if (request.getParameter("Delete") != null) {
        bean.removeStudents(new long[]{Long.parseLong(request.getParameter("ID"))});
    }

    if (request.getParameter("students") != null) {
        String[] checkedId = request.getParameterValues("students");
        long[] id = new long[checkedId.length];
        for (int i = 0; i < checkedId.length; i++) {
            id[i] = Long.parseLong(checkedId[i]);
        }
        if (request.getParameter("export") == null) {
            bean.removeStudents(id);
        } else {
            bean.exportStudents("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\students.xml", id);
        }
    }
    if (request.getParameter("import_sub") != null) {
        //    if(request.getParameter("file_to_import")!=null){

        bean.importStudents("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\students.xml");
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
        <%
            ArrayList<Integer> groupsNumber = bean.getGroupNumbers();
            for (int i = 0; i < groupsNumber.size(); i++) {%>
        <option><%=groupsNumber.get(i)%>
        </option>
        <% }
        %>
    </select>
        Date:<input type="date" name="DateCriteria"/>
        Curator ID:<select name="CuratorsCriteria">
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

    <form name="import" action="studentsTable.jsp" method="POST">
        <input type="file" name="file_to_import" value="Choose_File"/>
        <input type="submit" name="import_sub" value="Import"/>
    </form>
</div>
<div id="centerColumnStudents">
    <form name="studentsTable" action="studentsTable.jsp" method="GET">
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
                ArrayList<String> paramString = new ArrayList<>();
                ArrayList<String> argString = new ArrayList<>();
                String[] param, arg;
                if (name != null && !name.isEmpty()) {
                    paramString.add("NAME");
                    argString.add(name);
                }
                if (groupNumber != null && !groupNumber.isEmpty()) {
                    paramString.add("GROUP_NUMBER");
                    argString.add(groupNumber);
                }
                if (dateInput != null && !dateInput.isEmpty()) {
                    paramString.add("DATE");
                    argString.add(dateInput);
                }
                if (curator != null && !curator.isEmpty()) {
                    paramString.add("CURATOR");
                    if (curator == "") {
                        argString.add("0");
                    } else {
                        argString.add(curator);
                    }
                }
                param = new String[paramString.size()];
                arg = new String[paramString.size()];
                for (int i = 0; i < paramString.size(); i++) {
                    param[i] = paramString.get(i);
                    arg[i] = argString.get(i);
                }
                students = bean.getStudentsByCriterium(param, arg);
                ArrayList<Student> studentsFull = bean.getAllStudents();
                for (int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    ArrayList<Group> groupToRedirect = bean.getGroupsByCriterium(new String[]{"GROUP_NUMBER"}, new String[]{String.valueOf(student.getGROUP_STUDENT())});
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
</body>
</html>
