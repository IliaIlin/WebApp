<%-- 
    Document   : index
    Created on : 20.03.2015, 2:28:10
    Author     : Board1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/Style.css">
        <title>Index_Page</title>
    </head>
    <body>
        <div id="main">
            <div id="header">
                <h1>Student Directory</h1>
            </div>
            <div id="indexLeftColumn">    
                <a href="studentsTable.jsp"><img src="graduated.png" width="398" height="364" alt="graduated"/></a>
                <p>Students Table</p>
            </div>
            <div id="indexRightColumn">
                <a href="groupsTable.jsp"><img src="membership-icon.png" width="398" height="364" alt="membership-icon"/></a>
                <p>Groups Table</p>
            </div>
        </div>
        <a href="help.jsp">Help</a>
    </body>
</html>
