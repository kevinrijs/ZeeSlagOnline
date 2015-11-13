<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<title>Place boats</title>
</head>
<body>
	<h1>Battleship Online</h1>
	<h2>Place the boats</h2>
	<form method = "post" action="weetNogNietWatIkMetDezeGaDoen">
	<input type="hidden" id="xCoordinate"></input>
	<input type="hidden" id ="yCoordinate"></input>	</form>
	<canvas id="myCanvas" width="${dimensionX*1000}"
		height="${dimensionY*1000}"></canvas>
		

	$('#input1").text();
	$('form').submit();


	<script>
		var tableColumns = ${dimensionX};
		var tableRows = ${dimensionY};

		var canvas = document.getElementById('myCanvas');
		var context = canvas.getContext('2d');
		var startPositionsOfTilesX = [];
		var startPositionsOfTilesY = [];
		var tileWidth = 80;
		var tileHeight = 80;

		drawField(context, tableColumns, tableRows,startPositionsOfTilesX,startPositionsOfTilesY);
		
		
		
		
		

		
		function onClick(evt) { 
			var mousePosition = getMousePos(canvas, evt);
			
			var x = Math.floor(mousePosition.x/tileWidth);
			var y = Math.floor(mousePosition.y/tileHeight);
			
			if(x<tableColumns&&y<tableRows){
			
			$('#xCoordinate').text(x);
			$('#yCoordinate').text(y);
			$('form').submit();
			}
			else{
			alert('You clicked outside of the field')}

		}
		canvas.onclick = onClick;

		function getMousePos(canvas, evt) {
			var rect = canvas.getBoundingClientRect();
			return {
				x : evt.clientX - rect.left,
				y : evt.clientY - rect.top
			};
		}

		canvas.addEventListener('mousemove', function(evt) {
			var mousePos = getMousePos(canvas, evt);
			var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y;

		}, false);

		
		function drawField(context, tableColums, tableRows,startPositionsOfTilesX,startPositionsOfTilesY) {
			

			for (var i = 0; i < tableColumns; i++) {
				for (var j = 0; j < tableRows; j++) {

					var newX =(i * tileWidth);
					var newY=( j * tileHeight);
					context.beginPath();
					context.rect(newX ,newY, tileWidth, tileHeight);				
					context.lineWith = 1;
					context.strokeStyle = 'black';
					context.stroke();
					
					startPositionsOfTilesX.push(newX);
					startPositionsOfTilesY.push(newY);
					
				}
			}
		}

	</script>
</body>
</html>