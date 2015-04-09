<%-- 
    Document   : entityAddition
    Created on : Apr 2, 2015, 12:26:01 AM
    Author     : Илья
--%>

<%@page import="classes.Student"%>
<%@page import="classes.DataBaseStudentDaoImpl"%>
<%@page import="classes.Group"%>
<%@page import="classes.DataBaseGroupDaoImpl"%>
<%@page import="classes.DataSource"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/Style.css"/>
        <title>Student_Addition</title>
    </head>
    <body>
        <a href=studentsTable.jsp>Students Table</a>
        <h1 style="margin-top: 100px">Student Addition</h1>
        <form name="studentAddition" action="studentAddition.jsp" actionmethod="POST"> 
            <div class="inputStudent">
                Name:<input type="text" name="Name" value=""/>
            </div>
            <div class="inputStudent">
                Group number:<select name="GroupNumbers">
                    <%
                        DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
                        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
                        ArrayList<Integer> groupsNumber = dataBaseGroupDao.getGroupNumbers();
                        for (int i=0; i<groupsNumber.size();i++) {%>
                    <option> <%=groupsNumber.get(i)%></option>  
                    <%  }
                    %>
                </select>
            </div>
            <div class="inputStudent">
                Date:<input type="date" name="Date" />
            </div>  
            <div class="inputStudent">
                Curator ID:<select name="Curators">
                    <option>0</option>
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
             <% 
             if (request.getParameter("Name") != null 
             && !request.getParameter("Name").isEmpty()
             && request.getParameter("Date") != null) {
                 String dateInput= request.getParameter("Date");
                String [] dateArray=dateInput.split("-");
                String date=dateArray[2]+"."+dateArray[1]+"."+dateArray[0];
             
             if (Integer.parseInt(request.getParameter("Curators")) == 0){  // addition without curator
             dataBaseStudentDao.insertStudent(request.getParameter("Name"),
             Integer.parseInt(request.getParameter("GroupNumbers")),
             date);
             } else {
             dataBaseStudentDao.insertStudent(request.getParameter("Name"),
             Integer.parseInt(request.getParameter("GroupNumbers")),
             date, Integer.parseInt(request.getParameter("Curators")));
             }
             } 
        %>
        </form>
</body>
</html>
