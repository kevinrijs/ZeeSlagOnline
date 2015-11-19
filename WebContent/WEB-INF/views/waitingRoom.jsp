

<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<title>Waiting Room</title>
<link rel="stylesheet" href="http://localhost:8080/ZeeSlagOnline/resources/style.css">
</head>
<body>
<h1>
Game Lobby
</h1>
<form:form method="post" action="waitingRoomJoin" >
<table>

<tr>
<td></td>
<td>Id</td>
<td>Name:</td>
<td>Board size</td>
<td>Number of Boats</td>
</tr>



<c:forEach items ="${gameList}" var ="speler">
<tr>
<td><input type="radio" name="opponent" value="${speler.id}" required></td>
<td>${speler.id}</td>
<td>${speler.naam}</td>
<td>${speler.bord.bordBreedte} x ${speler.bord.bordLengte}</td>
<td>${speler.hoeveelheidBoten}</td>
</tr>

</c:forEach>
</table>
<table>
<tr>
<td>
<input class="button" type="submit" value="Join Game">
</form:form>
</td>

<td>
<form:form method="post" action="waitingRoomHost" >
<input class="button" id= "host" type="submit" value="Host Game">
</form:form></td>

</tr>

</table>








<a href ="<c:url value="/" />"	><button class="button" type="button">Back</button></a>


 <script>

if(${player1.host}){
setTimeout(function(){window.location.href='<c:url value="/waitingRoomRefresh" />'},5000);

$("#host").hide();
}


</script>  





</body>
</html>