<%-- 
    Document   : studentInfo
    Created on : Apr 18, 2015, 10:55:56 PM
    Author     : Илья
--%>

<%@page import="classes.Group"%>
<%@page import="classes.Student"%>
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
        <title>Student_Info</title>
    </head>
    <body>
        <% DataSource dataSource = new DataSource("pai", "11");
            DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
            DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
        %>

        <a href=studentsTable.jsp>Students Table</a>
        <h1 style="margin-top: 100px">Student Info</h1>
        <form name="studentInfo" action="studentsTable.jsp" actionmethod="POST">
            <div class="inputStudent">
                ID:<input type="number" name="ID"  value="<%=request.getParameter("StudentID")%>" readonly="true" />
            </div>
            <div class="inputStudent">
                Name:<input type="text" name="NameEditing" value="<%=request.getParameter("StudentNameToShow")%>"/>
            </div>
            <div class="inputStudent">
                Group number:<select name="GroupNumbersEditing">
                    <%
                        ArrayList<Integer> groupsNumber = dataBaseGroupDao.getGroupNumbers();
                        for (int i = 0; i < groupsNumber.size(); i++) {
                            if (groupsNumber.get(i) != Integer.parseInt(request.getParameter("StudentGroupToShow"))) {%>
                    <option> <%=groupsNumber.get(i)%></option>  
                    <%  } else {%>
                    <option selected="true"><%=groupsNumber.get(i)%></option>
                    <% }
                        }
                    %>
                </select>
            </div>
            <div class="inputStudent">
                Date:<input type="date" name="DateEditing" value="<%=request.getParameter("StudentDateToShow")%>" />
            </div>  
            <div class="inputStudent">
                Curator ID:<select name="CuratorsEditing" >
                    <option value="0"></option>
                    <%  ArrayList<Student> students = dataBaseStudentDao.getAllStudents();
                        for (Student student : students) {
                            if (student.getID() == Long.parseLong(request.getParameter("StudentCuratorToShow"))) {%>
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
                <input type="submit" name="Edit" value="Edit Student" />
                <input type="submit" name="Delete" value="Delete Student" />
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
                            <!-- <th style="width:100px"></th> -->
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            String curator = request.getParameter("StudentID");
                            students = dataBaseStudentDao.selectStudents(new String[]{"CURATOR"}, new String[]{curator});
                            ArrayList<Student> studentsFull = dataBaseStudentDao.getAllStudents();
                            for (int i = 0; i < students.size(); i++) {
                                Student student = students.get(i);
                                ArrayList<Group> groupToRedirect = dataBaseGroupDao.selectGroups(new String[]{"GROUP_NUMBER"}, new String[]{String.valueOf(student.getGROUP_STUDENT())});
                                Group group = groupToRedirect.get(0);

                        %>
                        <tr>
                            <td><input type="checkbox" name="students" value="<%=student.getID()%>"</td>
                            <td> <a href="http://localhost:8080/WebApp/studentInfo.jsp?StudentID=<%=String.valueOf(student.getID())%>&StudentNameToShow=<%=student.getNAME()%>&StudentGroupToShow=<%=String.valueOf(student.getGROUP_STUDENT())%>&StudentDateToShow=<%=String.valueOf(student.getDATE_ENROLLMENT())%>&StudentCuratorToShow=<%=student.getID_CURATOR()%>"><%=String.valueOf(student.getNAME())%></a></td>
                            <td> <a href="http://localhost:8080/WebApp/groupInfo.jsp?GroupID=<%=String.valueOf(group.getID())%>&GroupNumberToShow=<%=String.valueOf(group.getGROUP_NUMBER())%>&FacultyToShow=<%=String.valueOf(group.getFACULTY())%>"><%=String.valueOf(student.getGROUP_STUDENT())%></a></td>
                            <td> <%=String.valueOf(student.getDATE_ENROLLMENT())%></td>
                            <% if (Integer.parseInt(String.valueOf(student.getID_CURATOR())) == 0) {%>
                            <td> <%=""%> </td> 
                            <%   } else {%>
                            <td><% long id = student.getID_CURATOR();
                                A:
                                for (int j = 0; j < studentsFull.size(); j++) {
                                    if (studentsFull.get(j).getID() == id) {%>
                                <%=studentsFull.get(j).getNAME()%>
                                <%   break A;
                                    }
                                }
                                %>                            
                            </td>


                            <%
                                }%>
                            <td><a href="http://localhost:8080/WebApp/studentEditing.jsp?StudentID=<%=String.valueOf(student.getID())%>&StudentNameToEdit=<%=student.getNAME()%>&StudentGroupToEdit=<%=String.valueOf(student.getGROUP_STUDENT())%>&StudentDateToEdit=<%=String.valueOf(student.getDATE_ENROLLMENT())%>&StudentCuratorToEdit=<%=student.getID_CURATOR()%>">
                                    <input type="button" name="Edit" value="Edit"/></a></td>
                            <!--  <td><a href="">Delete</a></td> -->
                        </tr>
                        <%   }

                        %>
                    </tbody>
                </table> 
            </form>
        </div>
    </body>
</html>
