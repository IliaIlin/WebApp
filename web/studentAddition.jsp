<%-- 
    Document   : entityAddition
    Created on : Apr 2, 2015, 12:26:01 AM
    Author     : Илья
--%>

<%@page import="classes.Student"%>
<%@page import="classes.DataBaseStudentDaoImpl"%>
<%@page import="classes.Group"%>
<%@page import="classes.DataBaseGroupDaoImpl"%>
<%@page import="classes.DataSourcePool"%>
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
                        DataSourcePool dataSource = new DataSourcePool();
                        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
                        ArrayList<Integer> groupsNumber = dataBaseGroupDao.getGroupNumbers();
                        for (int i = 0; i < groupsNumber.size(); i++) {%>
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
                    <option value="0"></option>
                    <% DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
                        ArrayList<Student> students = dataBaseStudentDao.getAllStudents();
                        for (Student student : students) {%>
                    <option value="<%=student.getID()%>"> <%=student.getNAME()%></option>
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
                    String dateInput = request.getParameter("Date");
                    if (request.getParameterValues("Curators")[0] == "0") {  // addition without curator
                        dataBaseStudentDao.insertStudent(request.getParameter("Name"),
                                Integer.parseInt(request.getParameter("GroupNumbers")),
                                dateInput);
                    } else {
                        dataBaseStudentDao.insertStudent(request.getParameter("Name"),
                                Integer.parseInt(request.getParameter("GroupNumbers")),
                                dateInput, Integer.parseInt(request.getParameterValues("Curators")[0]));
                    }
                }
                
                dataSource.close();
            %>
        </form>
    </body>
</html>
