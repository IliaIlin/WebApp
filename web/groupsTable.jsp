<%-- 


ment   : groupsTable
    Created on : Mar 27, 2015, 2:18:04 AM
    Author     : Илья
--%>

<%@page import="java.util.Arrays"%>
<%@page import="java.util.Enumeration"%>
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
        <%DataSource dataSource = new DataSource("SYSTEM", "21071994Rer");
            DataBaseGroupDaoImpl dataBaseGroupDao = new DataBaseGroupDaoImpl(dataSource.getConnection());           
            if (request.getParameter("groups") != null) {
                ArrayList<Long> emptyIDs=dataBaseGroupDao.getEmptyGroupIDs();
                String[] idToDelete = request.getParameterValues("groups");
                ArrayList<Long> id=new ArrayList<>();
               for (int i = 0; i < idToDelete.length; i++) {
                   if(emptyIDs.contains(Long.parseLong(idToDelete[i]))){
                    id.add(Long.parseLong(idToDelete[i]));
               }
               }
               long[] idToDeleteEmpty=new long[id.size()];
               for(int i=0; i<id.size();i++){
                   idToDeleteEmpty[i]=id.get(i);
               }
              if(idToDeleteEmpty.length!=0){
               dataBaseGroupDao.deleteGroups(idToDeleteEmpty);
              }
            }
        %>
        <div class="header">
            <h1>Groups Table</h1>
        </div>

        <div id="centerColumnGroups">
            <form name="Add" action="groupAddition.jsp">
                <input type="submit" value="Add Group" />
            </form>
            <form name="Table" action="groupsTable.jsp" method="GET">
                <input type="submit" value="Delete"/>
                <table border="1" >
                    <thead>
                        <tr>
                            <th></th>
                            <th style="width:100px">group №</th>
                            <th style="width:100px">Faculty</th>
                            <th style="width:100px"></th>
                            <th style="width:100px"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Group> groups = dataBaseGroupDao.getAllGroups();
                            for (int i = 0; i < groups.size(); i++) {
                                Group group = groups.get(i);
                        %>
                        <tr>
                            <td><input type="checkbox" name="groups" value="<%=String.valueOf(group.getID())%>"/></td>
                            <td> <%=String.valueOf(group.getGROUP_NUMBER())%></td>
                            <td> <%=String.valueOf(group.getFACULTY())%></td>
                            <td><a href="">Edit</a></td>
                            <td><a href="">Delete</a></td>
                        </tr>

                        <%
                            }

                        %>
                    </tbody>
                </table>
            </form>
        </div> 
    </body>
</html>
