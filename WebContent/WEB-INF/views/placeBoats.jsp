<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Place boats</title>
</head>
<body>
	<h1>Battleship Online</h1>
	<h2>Place the boats</h2>
	<canvas id="myCanvas" width="${width}*10" height="${height}*10"></canvas>




	<script>
		var maxWidth = ${width};
		var maxHeight = ${height};

		var canvas = document.getElementById('myCanvas');
		var context = canvas.getContext('2d');

		function drawField(context, maxWidth, maxHeight) {

			for (var i = 0; i < maxWidth; i++) {
				for (var j = 0; j < maxHeight; j++) {
					context.beginPath();
					context.rect(i+10,j+10,10,10);
					context.
				}
			}
		}
	</script>
</body>
</html>