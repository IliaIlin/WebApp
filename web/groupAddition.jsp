<%-- 
    Document   : groupAddition
    Created on : Apr 3, 2015, 12:27:40 AM
    Author     : Илья
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="org.webapp.beans.WebAppBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/Style.css"/>
        <title>Group_Addition</title>
    </head>
    <body>
        <% WebAppBean bean = new WebAppBean(); %> 
        <a href=groupsTable.jsp>Groups Table</a>     
        <h1 style="margin-top: 100px">Group Addition</h1>
        <form name="groupAddition" action="groupAddition.jsp" actionmethod="POST">
            <div class="inputGroup">
                Group number:<input type="number" name="GroupNo"  min="1001" max="9999"/>
            </div>
            <div class="inputGroup">
                Faculty:<input type="text" name="Faculty" value=""/>
            </div>
            <br/>
            <div id="button">
                <input type="submit" value="Submit" />
            </div>
            <%
                boolean flag = true;
                if (request.getParameter("GroupNo") != null
                        && request.getParameter("Faculty") != null && !request.getParameter("Faculty").isEmpty()) {
                    ArrayList<Integer> groupNumbers = bean.getGroupNumbers();
                    if (groupNumbers.contains(Integer.parseInt(request.getParameter("GroupNo")))) {
                        flag = false;
                    }
                    if (flag) {
                        bean.addGroup(Integer.parseInt(request.getParameter("GroupNo")),
                                request.getParameter("Faculty"));
                    }
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", "http://localhost:8080/WebApp/groupsTable.jsp");
                }
            %>
        </form>
        <% bean.remove();%>
    </body>
</html>
