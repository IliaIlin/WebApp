<%-- 
    Document   : groupInfo
    Created on : Apr 18, 2015, 10:55:40 PM
    Author     : Илья
--%>

<%@page import="classes.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes.DataBaseStudentDaoImpl"%>
<%@page import="classes.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/Style.css"/>
        <title>Group_Info</title>
    </head>
    <body>
        <% DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
            DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
        %>

        <a href=groupsTable.jsp>Groups Table</a>
        <h1 style="margin-top: 100px">Group Info</h1>
        <form name="groupInfo" action="groupsTable.jsp" actionmethod="POST">
            <div class="inputGroup">
                ID:<input type="number" name="ID"  value="<%=request.getParameter("GroupID")%>" readonly="true" />
            </div>
            <div class="inputGroup">
                Group number:<input type="number" name="GroupNo"  min="1001" max="9999" value="<%=request.getParameter("GroupNumberToShow")%>"/>
            </div>
            <div class="inputGroup">
                Faculty:<input type="text" name="Faculty" value="<%=request.getParameter("FacultyToShow")%>"/>
            </div>
            <br/>
            <div id="button">
                <input type="submit" name="Edit" value="Edit Group" />
                <input type="submit" name="Delete" value="Delete Group" />
            </div>
        </form>
         
                 <div id="centerColumnStudents"> 
                     <form name="Table" action="studentsTable.jsp" method="GET">
                <input type="submit" value="Delete"/>
                <table border="1" >                                     
                    <thead>
                        <tr>
                            <th></th>
                            <th style="width:100px">Name</th>
                            <th style="width:100px">group №</th>
                            <th style="width:100px">Date</th>
                            <th style="width:100px">Curator</th>
                            <th style="width:100px"></th>
                            <th style="width:100px"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Student> students = dataBaseStudentDao.selectStudents(new String[]{"GROUP_NUMBER"},new String[]{request.getParameter("GroupNumberToShow")});     
                            ArrayList<Student> studentsFull=dataBaseStudentDao.getAllStudents();
                            for (int i = 0; i < students.size(); i++) {
                                Student student = students.get(i);

                        %>
                        <tr>
                            <td><input type="checkbox" name="students" value="<%=student.getID()%>"</td>
                            <td> <a href=""><%=String.valueOf(student.getNAME())%></a></td>
                            <td> <a href=""><%=String.valueOf(student.getGROUP_STUDENT())%></a></td>
                            <td> <%=String.valueOf(student.getDATE_ENROLLMENT())%></td>
                            <% if (Integer.parseInt(String.valueOf(student.getID_CURATOR())) == 0) {%>
                            <td> <%=""%> </td> 
                            <%   } else {%>
                            <td><% long id=student.getID_CURATOR();                            
                            A:  for(int j=0; j<studentsFull.size();j++){
                                 if(studentsFull.get(j).getID()==id){ %>
                                     <%=studentsFull.get(j).getNAME()%>
                                  <%   break A;
                                 }
                             }
                                %>                            
                                </td>


                            <%
                                } %>
                            <td><a href="http://localhost:8080/WebApp/studentEditing.jsp?StudentID=<%=String.valueOf(student.getID())
                            %>&StudentNameToEdit=<%=student.getNAME()
                            %>&StudentGroupToEdit=<%=String.valueOf(student.getGROUP_STUDENT())%>&StudentDateToEdit=<%=String.valueOf(student.getDATE_ENROLLMENT())
                            %>&StudentCuratorToEdit=<%=student.getID_CURATOR()%>">
                                    <input type="button" name="Edit" value="Edit"/></a></td>
                            <td><a href="">Delete</a></td>
                        </tr>
                        <%   }

                        %>
                    </tbody>
                </table> 
                        </form>
        </div>
    </body>
</html>
