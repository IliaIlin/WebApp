<%-- 
    Document   : editStudent
    Created on : Apr 13, 2015, 12:37:00 AM
    Author     : Илья
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="classes.DataBaseGroupDaoImpl"%>
<%@page import="classes.DataSourcePool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/Style.css"/>
        <title>Group_Editing</title>
    </head>
    <body>
        <a href=groupsTable.jsp>Groups Table</a>
        <h1 style="margin-top: 100px">Group Editing</h1>
        <form name="groupEditing" action="groupsTable.jsp" actionmethod="POST">
            <div class="inputGroup">
                ID:<input type="number" name="ID"  value="<%=request.getParameter("GroupID")%>" readonly="true" />
            </div>
            <div class="inputGroup">
                Group number:<input type="number" name="GroupNo"  min="1001" max="9999" value="<%=request.getParameter("GroupNumberToEdit")%>"/>
            </div>
            <div class="inputGroup">
                Faculty:<input type="text" name="Faculty" value="<%=request.getParameter("FacultyToEdit")%>"/>
            </div>
            <br/>
            <div id="button">
                <input type="submit" value="Submit" />
            </div>
        </form>
    </body>
</html>
