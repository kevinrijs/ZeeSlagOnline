

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
<td>Your name:</td>
<td>Board size</td>
<td>Number of Boats</td>
</tr>
<tr>
<td>${name}</td>
<td>${dimensionX} x ${dimensionY}</td>
<td>${numberOfBoats}</td>
</tr>
</table>

<a href ="<c:url value="/" />"	>  <button type="button">Back</button></a>
<script>setTimeout(function(){window.location.href='<c:url value="/waitingRoom" />'},5000);</script>





</body>
</html>