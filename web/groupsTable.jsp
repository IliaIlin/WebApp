<%-- 
    Document   : groupsTable
    Created on : Mar 27, 2015, 2:18:04 AM
    Author     : Илья
--%>

<%@page import="classes.Group"%>
<%@page import="classes.DataBaseGroupDaoImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes.DataSource"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <title>Groups Table</title>
    </head>
    <body>       
        <div class="header">
            <h1>Groups Table</h1>
        </div>
        <div id="centerColumnGroups">
            <table border="1" >
                <thead>
                    <tr>
                        <th style="width:100px">group №</th>
                        <th style="width:100px">Faculty</th>
                    </tr>
                </thead>
                <tbody>
                  <%
                        DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
                        DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());
                        ArrayList<Group> groups  = dataBaseGroupDao.getAllGroups();

        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
              %>
              <tr>
                  <td> <%=String.valueOf(group.getGROUP_NUMBER())%></td>
                  <td> <%=String.valueOf(group.getFACULTY())%></td>
              </tr>
              <%
        }
        %>
                </tbody>
            </table>
        </div>
        <div class="indexRightColumn">
        </div>
    </body>
</html>
