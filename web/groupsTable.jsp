<%-- 


ment   : groupsTable
    Created on : Mar 27, 2015, 2:18:04 AM
    Author     : Илья
--%>


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
<% WebAppBean bean = new WebAppBean();
    if (request.getParameter("GroupNo") != null) {
        ArrayList<Integer> groupNumbers = bean.getGroupNumbers();
        if (!groupNumbers.contains(Integer.parseInt(request.getParameter("GroupNo")))) {
            bean.editGroup(Long.parseLong(request.getParameter("ID")),
                    new String[]{"GROUP_NUMBER", "FACULTY"}, new String[]{request.getParameter("GroupNo"), request.getParameter("Faculty")});
        } else {
            bean.editGroup(Long.parseLong(request.getParameter("ID")),
                    new String[]{"FACULTY"}, new String[]{request.getParameter("Faculty")});
        }
    }
    if (request.getParameter("Delete") != null) {
        ArrayList<Long> emptyIDs = bean.getEmptyGroupIDs();
        if (emptyIDs.contains(Long.parseLong(request.getParameter("ID")))) {
            bean.removeGroups(new long[]{Long.parseLong(request.getParameter("ID"))});
        }
    }

    if (request.getParameter("groups") != null) {
        ArrayList<Long> emptyIDs = bean.getEmptyGroupIDs();
        String[] checkedId = request.getParameterValues("groups");
        if (request.getParameter("export") == null) {
            ArrayList<Long> id = new ArrayList<>();
            for (int i = 0; i < checkedId.length; i++) {
                if (emptyIDs.contains(Long.parseLong(checkedId[i]))) {
                    id.add(Long.parseLong(checkedId[i]));
                }
            }
            long[] idToDeleteEmpty = new long[id.size()];
            for (int i = 0; i < id.size(); i++) {
                idToDeleteEmpty[i] = id.get(i);
            }
            if (idToDeleteEmpty.length != 0) {
                bean.removeGroups(idToDeleteEmpty);
            }
        } else {
            long[] id = new long[checkedId.length];
            for (int i = 0; i < checkedId.length; i++) {
                id[i] = Long.parseLong(checkedId[i]);
            }
            bean.exportGroups("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\groups.xml", id);
        }
    }
    if (request.getParameter("import_sub") != null) {
        //    if(request.getParameter("file_to_import")!=null){
        bean.importGroups("C:\\Users\\Илья\\Documents\\NetBeansProjects\\WebApp\\groups.xml");
        // }
    }
%>
<div class="header">
    <h1>Groups Table</h1>
</div>

<div id="button">
    <form name="Add" action="groupAddition.jsp">
        <input type="submit" value="Add Group"/>
    </form>
    <form name="import" action="groupsTable.jsp" method="POST">
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
                ArrayList<Group> groups = bean.getAllGroups();
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
<%
    bean.remove();
%>
</body>
</html>
