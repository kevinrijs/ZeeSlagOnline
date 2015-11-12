<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ZeeSlag Online</title>
</head>
<body>
${hoi}
<form:form method="post" action="/startGame">
		<p>
		<table>
		<tr>
		<td>Player Name:</td><td><input type="text" name="name1"></td>
		</tr>
		<tr>
		<td>Opponent</td><td><input type="radio" name="opponent" value="player">Human<input type="radio" name="opponent" value="computer" checked>Computer</td>
		</tr>
		<tr>
		<td>Board Dimensions: X</td><td><input type="number" name="dimensionX" min="1" max="10">Y<input type="number" name="dimensionX" min="1" max="10"></td>
		</tr>
		<tr>
		<td>Number of Boats</td><td><input type="number" name="dimensionX" min="1" max="5"></td>
		</tr>
		<tr>
		<td></td><td><input type="submit" value="Start game"></td>
		</tr>
		</table>
			
			
			
		</p>
	</form:form>
</body>
</html>