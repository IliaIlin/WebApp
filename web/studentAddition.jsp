<%-- 
    Document   : entityAddition
    Created on : Apr 2, 2015, 12:26:01 AM
    Author     : Илья
--%>

<%@page import="classes.Student"%>
<%@page import="classes.Group"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes.DataBaseGroupDaoImpl"%>
<%@page import="classes.DataBaseStudentDaoImpl"%>
<%@page import="classes.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/Style.css"/>
        <title>Student_Addition</title>
    </head>
    <body>
            <h1 style="margin-top: 100px">Student Addition</h1>
            <form name="studentAddition" action="studentsTable.jsp" actionmethod="GET"> 
                <div class="inputStudent">
                Name:<input type="text" name="Name" value=""/>
                </div>
                <div class="inputStudent">
                Group number:<select name="GroupNumbers">
                    <%
                        DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
                        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
                        ArrayList<Group> groups = dataBaseGroupDao.getAllGroups();
                    for (Group group : groups) {%>
                    <option> <%=group.getGROUP_NUMBER()%></option>  
                    <%  }
                    %>
                </select>
                </div>
                <div class="inputStudent">
                Date:<input type="date" name="Date" value="dd.mm.yy" />
                </div>  
                <div class="inputStudent">
                Curator ID:<select name="Curators">
                    <option>-1</option>
                    <% DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
                   ArrayList<Student> students = dataBaseStudentDao.getAllStudents();
                   for (Student student : students) {%>
                    <option> <%=student.getID()%></option>
                    <% }
                    %>
                </select>
                </div>
                <br/>
                <div id="button">
                    <input type="submit" value="Submit" />
                </div>
            </form>
        </form>
</body>
</html>
