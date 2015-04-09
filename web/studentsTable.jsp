<%-- 
    Document   : studentsTable
    Created on : Mar 27, 2015, 1:17:32 AM
    Author     : Илья
--%>

<%@page import="classes.Student"%>
<%@page import="classes.DataBaseStudentDaoImpl"%>
<%@page import="classes.DataSource"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <title>Students Table</title>
    </head>
    <body>
        <div class="header">
            <h1>Students Table</h1>
        </div>
        <div id="centerColumnStudents"> 
            <form name="Add" action="studentAddition.jsp">
                <input type="submit" value="Add Student" />               
            </form>
            <form name="Delete" action="studentsTable.jsp">
                <input type="submit" value="Delete Students" />
            </form>
            <table border="1" >
                <thead>
                    <tr>
                        <th></th>
                        <th style="width:100px">Name</th>
                        <th style="width:100px">group №</th>
                        <th style="width:100px">Date</th>
                        <th style="width:100px">Curator</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
                        DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
                        ArrayList<Student> students = dataBaseStudentDao.getAllStudents();

                        for (int i = 0; i < students.size(); i++) {
                            Student student = students.get(i);
                    %>
                    <tr>
                        <td><input type="checkbox" name="students"</td>
                        <td> <a href=""><%=String.valueOf(student.getNAME())%></a></td>
                        <td> <a href=""><%=String.valueOf(student.getGROUP_STUDENT())%></a></td>
                        <td> <%=String.valueOf(student.getDATE_ENROLLMENT())%></td>
                        <% if(Integer.parseInt(String.valueOf(student.getID_CURATOR()))==0){ %>
                            <td> <%=""%> </td>
                     <%   }
                        else{ %>
                        <td> <a href=""><%=String.valueOf(student.getID_CURATOR())%>                            
                        </a></td>
                    </tr>
                    <%
                        }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
