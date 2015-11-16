

<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<title>Waiting Room</title>
</head>
<body>
<p>
Waiting for a Human opponent to kick your ass...
</p>
<table>

<tr>
<td></td>
<td>Id</td>
<td>Name:</td>
<td>Board size</td>
<td>Number of Boats</td>
</tr>

<form:form method="post" action="waitingRoomJoin" >
<c:forEach items ="${gameList}" var ="speler">
<tr>
<td><input type="radio" name="opponent" value="${speler.id}"></td>
<td>${speler.id}</td>
<td>${speler.naam}</td>
<td>${speler.bord.bordBreedte} x ${speler.bord.bordLengte}</td>
<td>${speler.hoeveelheidBoten}</td>
</tr>

</c:forEach>
<tr>


</tr>

</table>

<a href ="<c:url value="/" />"	>  <button type="button">Back</button></a>
<input type="submit" value="Join Game">
</form:form>


<form:form method="post" action="waitingRoomHost" >
<input type="submit" value="Host Game">
</form:form>



  <script>

if(${player1.host}){
setTimeout(function(){window.location.href='<c:url value="/waitingRoomRefresh" />'},5000);
}



</script>  





</body>
</html>