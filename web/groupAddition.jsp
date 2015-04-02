<%-- 
    Document   : groupAddition
    Created on : Apr 3, 2015, 12:27:40 AM
    Author     : Илья
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/Style.css"/>
        <title>Group_Addition</title>
    </head>
    <body>
       <h1 style="margin-top: 100px">Group Addition</h1>
       <form name="groupAddition" action="groupsTable.jsp" actionmethod="GET">
           <div class="inputGroup">
               Group number:<input type="number" name="GroupNo" value=""/>
           </div>
           <div class="inputGroup">
           Faculty:<input type="text" name="Faculty" value=""/>
           </div>
           <br/>
                <div id="button">
                    <input type="submit" value="Submit" />
                </div>
       </form>
    </body>
</html>
