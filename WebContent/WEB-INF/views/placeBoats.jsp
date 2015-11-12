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
	<canvas id="myCanvas" width="${dimensionX*1000}" height="${dimensionY*1000}"></canvas>




	<script>
		var tableColumns = ${dimensionX};
		var tableRows = ${dimensionY};
		

		var canvas = document.getElementById('myCanvas');
		var context = canvas.getContext('2d');
		
					

		drawField(context,tableColumns,tableRows);
		
		function drawField( context, tableColums, tableRows) {
		var tileWidth = 100;
		var tileHeight = 100;

			for (var i = 0; i < tableColumns; i++) {
				for (var j = 0; j < tableRows; j++) {
					context.beginPath();
					context.rect(i * 100, j * 100, tileWidth, tileHeight);
					
					
					context.lineWith = 1;
					context.strokeStyle = 'black';
					context.stroke();
				}
			}
		}
	</script>
</body>
</html>