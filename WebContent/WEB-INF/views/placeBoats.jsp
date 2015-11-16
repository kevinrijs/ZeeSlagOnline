<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<title>Place boats</title>

</head>
<body>
	<h1>Battleship Online</h1>
	<h2>Place the boats</h2>
	<form:form name="formnaam" method = "post" action="placeBoats">
	<input type="hidden" id="xCoordinate" name="xCoordinate"></input>
	<input type="hidden" id ="yCoordinate" name="yCoordinate"></input>
	<h4>Boat orientation:</h4>
	<input type="radio" name="orientation" value="false" checked>Up
	<br>
	<input type="radio" name="orientation" value="true">Right
	<h4>Select Boat</h4>
	<input id="aircraftcarrier" type="radio" name="boatType" value = "0" >Aircraft Carrier
	<input id="battleship" type="radio" name="boatType" value = "1">Battleship
	<input id="submarine" type="radio" name="boatType" value = "2">Submarine
	<input id="cruiser" type="radio" name="boatType" value = "3">Cruiser
	<input id="patrolboat" type="radio" name="boatType" value = "4" checked>Patrol boat
	
	</form:form>
	<canvas id="myCanvas" width="${dimensionX*1000}"
		height="${dimensionY*1000}"></canvas>
		




	<script>
		var tableColumns = ${dimensionX};
		var tableRows = ${dimensionY};

		var canvas = document.getElementById('myCanvas');
		var context = canvas.getContext('2d');
		var startPositionsOfTilesX = [];
		var startPositionsOfTilesY = [];
		var tileWidth = 50;
		var tileHeight = 50;
		
		var botenArray = [];	
		<c:forEach var="vakje" items="${player1.getBord().getVakjeArray()}">
				botenArray.push(${vakje.isBevatBoot()});
		</c:forEach> 

		drawField(context, tableColumns, tableRows,startPositionsOfTilesX,startPositionsOfTilesY);
	
		
		
		

		
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
					
					 newX =(j * tileWidth);
					 newY=( i * tileHeight);
					
					
					context.beginPath();
					context.rect(newX ,newY, tileWidth, tileHeight);
					
										
					context.lineWith = 1;
					context.strokeStyle = 'black';

					context.stroke();
					
					if(botenArray[j*${dimensionX}+i]===true){
						drawBoats(newX,newY,tileWidth,tileHeight);}
					}
					
					startPositionsOfTilesX.push(newX);
					startPositionsOfTilesY.push(newY);
					
				}
			}
		
		
		function drawBoats(){
		context.beginPath();
		context.fillStyle='#8ED6FF';
		context.fillRect(newX ,newY, tileWidth, tileHeight);
		context.stroke();
		}
		
		

	</script>
</body>
</html>