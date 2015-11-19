<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://localhost:8080/ZeeSlagOnline/resources/style.css">
<title>ZeeSlag Online</title>
</head>
<body>
<h1>Welcome to ZeeSlag Online</h1>
<form:form method="post" action="startGame" >
		
		<table>
		<tr>
		<td>Player Name:</td><td><input type="text" name="name" value="${name}" required></td>
		</tr>
		<tr>
		<td>Opponent</td><td><input type="radio" name="opponent" value="player" checked>Human<input type="radio" name="opponent" value="computer">Computer</td>
		</tr>
		<tr>
		<td>Board Dimensions: X</td><td><input type="number" name="dimensionX" min="1" max="10" value="10" size="2" required>
										Y<input type="number" name="dimensionY" min="1" max="10" value="10" size="2" required></td>
		</tr>
		<tr>
		<td>Number of Boats</td><td><input type="number" name="boats" min="1" max="5" value="5" size="1" required></td>
		</tr>
		<tr>
		<td></td><td><input type="submit" value="Start game" ></td>
		</tr>
		</table>
			
			
			
		
	</form:form>
</body>
</html>