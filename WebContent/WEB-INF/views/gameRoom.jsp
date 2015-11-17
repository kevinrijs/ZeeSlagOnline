<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<title>Game Room</title>
</head>
<body>
<canvas id="canvasOwn" width="${player1.bord.bordBreedte*1000}}"
		height="${player1.bord.bordLengte*1000}"></canvas>
<canvas id="canvasOther" width="${player1.bord.bordBreedte*1000}}"
		height="${player1.bord.bordLengte*1000}"></canvas>
		

<script>

		var tableColumns = ${player1.bord.bordBreedte};
		var tableRows = ${player1.bord.bordLengte};

		var canvas = document.getElementById('canvasOwn');
		var context = canvas.getContext('2d');
		var startPositionsOfTilesX = [];
		var startPositionsOfTilesY = [];
		var tileWidth = 50;
		var tileHeight = 50;
		
		var canvas1 = document.getElementById('canvasOther');
		
		
		drawFieldOwnBoard(context, tableColums, tableRows,startPositionsOfTilesX,startPositionsOfTilesY);
		drawFieldOtherBoard
		

			<!-- draws the field with the supplied dimensions-->
		function drawFieldOwnBoard(context, tableColums, tableRows,startPositionsOfTilesX,startPositionsOfTilesY) {
			

			for (var i = 0; i < tableColumns; i++) {
				for (var j = 0; j < tableRows; j++) {
					
					 newX =(j * tileWidth);
					 newY=( i * tileHeight);
					
					
					context.beginPath();
					context.rect(newX ,newY, tileWidth, tileHeight);
					var imageObj = new Image();
					imageObj.src ='http://mirror2.cze.cz/textures/water-texture-3.jpg';
					var pattern = context.createPattern(imageObj, 'repeat');
					context.fillStyle = pattern;
					context.fill();
									
					context.lineWith = 1;
					context.strokeStyle = 'black';

					context.stroke();
					
					if(botenArray[j*${player1.bord.bordBreedte}+i]===true){
						drawBoats(newX,newY,tileWidth,tileHeight);}
					}
					
					startPositionsOfTilesX.push(newX);
					startPositionsOfTilesY.push(newY);
					
				}
			}
			
				<!-- fills the tiles where boats are located-->
		function drawBoats(){
		context.beginPath();
		context.fillStyle='red';
		context.fillRect(newX ,newY, tileWidth, tileHeight);
		context.stroke();
		}
		
			<!-- Takes care of the onclick event on the board-->
		function onClick(evt) { 
			var mousePosition = getMousePos(canvas, evt);
			
			var x = Math.floor(mousePosition.x/tileWidth);
			var y = Math.floor(mousePosition.y/tileHeight);
			
			if(x<tableColumns&&y<tableRows){
			
			$('#xCoordinate').val(x);
			$('#yCoordinate').val(y);
			$('form').submit();
			}
			else{
			alert('You clicked outside of the field')}

		}
		
		canvas.onclick = onClick;

		<!-- Returns the mouse position at the time of the click-->
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
</script>

</body>
</html>