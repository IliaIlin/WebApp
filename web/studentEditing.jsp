<%-- 
    Document   : studentEditing
    Created on : Apr 14, 2015, 11:15:03 PM
    Author     : Илья
--%>

<%@page import="classes.Student"%>
<%@page import="classes.DataBaseStudentDaoImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes.DataBaseGroupDaoImpl"%>
<%@page import="classes.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/Style.css"/>
        <title>Student_Editing</title>
    </head>
    <body>
        <%DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
            DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
            DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());

        %>
        <a href=studentsTable.jsp>Students Table</a>
        <h1 style="margin-top: 100px">Student Editing</h1>
        <form name="studentEditing" action="studentsTable.jsp" actionmethod="POST">
            <div class="inputStudent">
                ID:<input type="number" name="ID"  value="<%=request.getParameter("StudentID")%>" readonly="true" />
            </div>
            <div class="inputStudent">
                Name:<input type="text" name="NameEditing" value="<%=request.getParameter("StudentNameToEdit")%>"/>
            </div>
            <div class="inputStudent">
                Group number:<select name="GroupNumbersEditing">
                    <%
                        ArrayList<Integer> groupsNumber = dataBaseGroupDao.getGroupNumbers();
                        for (int i = 0; i < groupsNumber.size(); i++) {
                            if (groupsNumber.get(i) != Integer.parseInt(request.getParameter("StudentGroupToEdit"))) {%>
                    <option> <%=groupsNumber.get(i)%></option>  
                    <%  } else {%>
                    <option selected="true"><%=groupsNumber.get(i)%></option>
                    <% }
                        }
                    %>
                </select>
            </div>
            <div class="inputStudent">
                Date:<input type="date" name="DateEditing" value="<%=request.getParameter("StudentDateToEdit")%>" />
            </div>  
            <div class="inputStudent">
                Curator ID:<select name="CuratorsEditing" >
                    <option value="0"></option>
                    <%  ArrayList<Student> students = dataBaseStudentDao.getAllStudents();
                        for (Student student : students) {
                            if (student.getID() == Long.parseLong(request.getParameter("StudentCuratorToEdit"))) {%>
                    <option selected="true" value="<%=student.getID()%>"> <%=student.getNAME()%></option>
                    <% } else if (student.getID() != Long.parseLong(request.getParameter("StudentID"))) {%>
                    <option value="<%=student.getID()%>"> <%=student.getNAME()%></option>    
                    <%   }
                        }
                    %>
                </select>
            </div>
            <br/>
            <div id="button">
                <input type="submit" value="Submit" />
            </div>
        </form>
    </body>
</html>
