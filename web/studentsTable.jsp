<%-- 
    Document   : studentsTable
    Created on : Mar 27, 2015, 1:17:32 AM
    Author     : Илья
--%>

<%@page import="classes.DataBaseGroupDaoImpl"%>
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
        <%DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
            DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
            DataBaseStudentDaoImpl dataBaseStudentDao = new DataBaseStudentDaoImpl(dataSource.getConnection());
            if (request.getParameter("students") != null) {
                String[] idToDelete = request.getParameterValues("students");
                long[] id = new long[idToDelete.length];
                for (int i = 0; i < idToDelete.length; i++) {
                    id[i] = Long.parseLong(idToDelete[i]);
                }
                dataBaseStudentDao.deleteStudents(id);
            }
        %>
        <div class="header">
            <h1>Students Table</h1>
        </div>
        <div id="Criteria">
            <form name="ViewCriteria" action="studentsTable.jsp" method="GET">
                Name:<input type="text" name="NameCriteria" value=""/>
                Group number:<select name="GroupNumbersCriteria">
                    <option></option>
                    <%
                        ArrayList<Integer> groupsNumber = dataBaseGroupDao.getGroupNumbers();
                        for (int i = 0; i < groupsNumber.size(); i++) {%>
                    <option> <%=groupsNumber.get(i)%></option>  
                    <%  }
                    %>
                </select>
                Date:<input type="date" name="DateCriteria"/>
                Curator ID:<select name="CuratorsCriteria">
                    <option></option>
                    <%
                        ArrayList<Student> students = dataBaseStudentDao.getAllStudents();
                        for (Student student : students) {%>
                    <option value="<%=student.getID()%>"> <%=student.getNAME()%> </option>
                    <% }
                    %>
                </select>
                <div id="button">
                    <input type="submit" value="Apply filters"/>
                    <a href="studentsTable.jsp"><input type="button" value="Clear"/></a> 
                </div>
            </form>
        </div> 

        <div id="centerColumnStudents"> 
            <form name="Add" action="studentAddition.jsp">
                <input type="submit" value="Add Student" />                
            </form>
            <form name="studentsTable" action="studentsTable.jsp" method="GET">
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
                            students = dataBaseStudentDao.getAllStudents();
                            String name = request.getParameter("NameCriteria");
                            String groupNumber = request.getParameter("GroupNumbersCriteria");
                            String dateInput = request.getParameter("DateCriteria");
                            String curator = request.getParameter("CuratorsCriteria");
                            //!!!!    if (name!=null && groupNumber==null) {
                            //        students = dataBaseStudentDao.selectStudents(new String[]{"NAME"}, new String[]{name});
                            //  }
                            // if (name != null && dateInput ==null) {
                            //    students=dataBaseStudentDao.selectStudents(new String[]{"NAME","GROUP_NUMBER","CURATOR"},new String[]{name,groupNumber,curator});
                            //  }
                            //   else if(name==null && dateInput!=null){
                            //    students=dataBaseStudentDao.selectStudents(new String[]{"DATE","GROUP_NUMBER","CURATOR"},new String[]{dateInput,groupNumber,curator});   
                            //   }
                            //   if (groupNumber != null) {
                            //    students=dataBaseStudentDao.selectStudents(new String[]{"GROUP_NUMBER"},new String[]{groupNumber});
                            //   }
                            //   if(dateInput!=null){
                            //       students=dataBaseStudentDao.selectStudents(new String[]{"DATE"},new String[]{"2015-04-16"});
                            //   }
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
                            <td> <a href=""><%=String.valueOf(student.getID_CURATOR())%>                            
                                </a></td>


                            <%
                                } %>
                            <td><a href="">Edit</a></td>
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
