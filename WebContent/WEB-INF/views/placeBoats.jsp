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
	<canvas id="myCanvas" width="${dimensionX*1000}"
		height="${dimensionY*1000}"></canvas>




	<script>
		var tableColumns = ${dimensionX};
		var tableRows = ${dimensionY};

		var canvas = document.getElementById('myCanvas');
		var context = canvas.getContext('2d');
		var startPositionsOfTilesX = [];
		var startPositionsOfTilesY = [];

		drawField(context, tableColumns, tableRows,startPositionsOfTilesX,startPositionsOfTilesY);
		
		
		
		
		

		
		function onClick(evt) { 
			var mousePosition = getMousePos(canvas, evt);
			
			var x = Math.floor(mousePosition.x/100);
			var y = Math.floor(mousePosition.y/100);
			
			alert('You clicked on tile: '+'x: '+x+' y: ' +y);

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

		
		function drawField(context, tableColums, tableRows,
				startPositionsOfTilesX, startPositionsOfTilesY) {
			var tileWidth = 100;
			var tileHeight = 100;

			for (var i = tableRows-1; i >= 0; i--) {
				var newY = (i * 100);
				startPositionsOfTilesY.push(newY);
				console.log("y: "+i);
				for (var j = 0; j < tableColumns; j++) {
					
					

					context.beginPath();
					context.rect(newX, newY, tileWidth, tileHeight);
					context.lineWith = 1;
					context.strokeStyle = 'black';
					context.stroke();
					
					var newX = (j * 100);
					startPositionsOfTilesX.push(newX);
					console.log("x: "+j);
				}
			}

		}
	</script>
</body>
</html>