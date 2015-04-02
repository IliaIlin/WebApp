<%-- 
    Document   : studentsTable
    Created on : Mar 27, 2015, 1:17:32 AM
    Author     : Илья
--%>

<%@page import="classes.DataSource"%>
<%@page import="classes.DataBaseStudentDaoImpl"%>
<%@page import="classes.Student"%>
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
            <table border="1" >
                <thead>
                    <tr>
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
                        <td> <%=String.valueOf(student.getNAME())%></td>
                        <td> <%=String.valueOf(student.getGROUP_STUDENT())%></td>
                        <td> <%=String.valueOf(student.getDATE_ENROLLMENT())%></td>
                        <td> <%=String.valueOf(student.getID_CURATOR())%></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <div class="indexRightColumn">
            <form name="Add" action="studentAddition.jsp">
                <input type="submit" value="Add Student" />
            </form>
        </div> 
                
        <%
            if (request.getParameter("Name") != null
                   && request.getParameter("Date") != null) {
                if(request.getParameter("Curators")=="-1"){  // addition without curator
                    dataBaseStudentDao.insertStudent(request.getParameter("Name"),
                            Integer.parseInt(request.getParameter("GroupNumbers")),
                                    request.getParameter("Date"));
                }
                else{
                   dataBaseStudentDao.insertStudent(request.getParameter("Name"),
                           Integer.parseInt(request.getParameter("GroupNumbers")),
                           request.getParameter("Date"),Integer.parseInt(request.getParameter("Curators")));
                                   
                } 
            }
            %>
    </body>
</html>
