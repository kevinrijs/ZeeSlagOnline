<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:csrfMetaTags />
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>

<title>Game Room</title>
</head>
<body>
<div>
<canvas id="canvasOwn" width="${player1.bord.bordBreedte*30}}"
		height="${player1.bord.bordLengte*30}"></canvas>
		</div><div>
<canvas id="canvasOther" width="${player1.bord.bordBreedte*50}}"
		height="${player1.bord.bordLengte*50}"></canvas>
		</div>


<script>
		// configureer JQuery om csrf-token mee te sturen
		var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		$(function () {
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
		});

		var tableColumns = ${player1.bord.bordBreedte};
		var tableRows = ${player1.bord.bordLengte};

		var canvas = document.getElementById('canvasOwn');
		var context = canvas.getContext('2d');
		var canvas1 = document.getElementById('canvasOther');
		var context1 = canvas1.getContext('2d');
		
		var startPositionsOfTilesX = [];
		var startPositionsOfTilesY = [];
		var tileWidthOwnBoard = 30;
		var tileHeightOwnBoard = 30;
		var tileWidthOtherBoard = 50;
		var tileHeightOtherBoard = 50;
		var backgroundSource= 'http://mirror2.cze.cz/textures/water-texture-3.jpg';
		
		var botenArrayOther = [];
		var beschotenBotenArrayOther=[];
		var botenArray = [];
		
			
		fillAllArrays();
		
		function fillAllArrays(){
		
		botenArrayOther.length =0;
		beschotenBotenArrayOther.length=0;
		botenArray.length = 0;
		
		<c:forEach var="vakje" items="${player2.bord.vakjeArray}"> 
				botenArrayOther.push(${vakje.bevatBoot});
		</c:forEach>
		
		
		<c:forEach var="vakje" items="${player2.bord.vakjeArray}"> 
				beschotenBotenArrayOther.push(${vakje.beschoten});
		</c:forEach>
		
		
		
		
		<c:forEach var="vakje" items="${player1.bord.vakjeArray}"> 
				botenArray.push(${vakje.bevatBoot});
		</c:forEach>
		}
		
		
		
		
		drawFieldUpperBoard(context, tableColumns, tableRows,startPositionsOfTilesX,startPositionsOfTilesY);
		drawFieldLowerBoard(context1,tableColumns,tableRows);
		
		function drawFieldLowerBoard(context1, tableColumns, tableRows){
		
		
		for (var i = 0; i < tableColumns; i++) {
				for (var j = 0; j < tableRows; j++) {
					var newX =(j * tileWidthOtherBoard);
					var newY=( i * tileHeightOtherBoard);
					
					context1.beginPath();
					context1.rect(newX ,newY, tileWidthOtherBoard, tileHeightOtherBoard);
					var imageObj = new Image();
					imageObj.src =backgroundSource;
					var pattern = context.createPattern(imageObj, 'repeat');
					context1.fillStyle = pattern;
					context1.fill();
					context1.lineWith = 1;
					context1.strokeStyle = 'black';

					context1.stroke();
					
					if(botenArrayOther[j*${player1.bord.bordBreedte}+i] && beschotenBotenArrayOther[j*${player1.bord.bordBreedte}+i]){
						drawBoatsOther(newX,newY,tileWidthOwnBoard,tileHeightOwnBoard);}
					}
					if(beschotenBotenArrayOther[j*${player1.bord.bordBreedte}+i]){
					drawShotBoatsOther(newX,newY,tileWidthOwnBoard,tileHeightOwnBoard);
					}
					
					
					
				}}
				
				function drawShotBoatsOther(newX,newY,tileWidthOwnBoard,tileHeightOwnBoard){
				context1.beginPath();
		context1.fillStyle='white';
		context1.fillRect(newX1 ,newY1, tileWidthOtherBoard, tileHeightOtherBoard);
		context1.stroke();
				}
		
		
		
		

			<!-- draws the field with the supplied dimensions-->
		function drawFieldUpperBoard(context, tableColumns, tableRows,startPositionsOfTilesX,startPositionsOfTilesY) {
			

			for (var i = 0; i < tableColumns; i++) {
				for (var j = 0; j < tableRows; j++) {
					
					var newX1 =(j * tileWidthOwnBoard);
					var newY1=( i * tileHeightOwnBoard);
					
					
					context.beginPath();
					context.rect(newX1 ,newY1, tileWidthOwnBoard, tileHeightOwnBoard);
					var imageObj = new Image();
					imageObj.src = backgroundSource;
					var pattern = context.createPattern(imageObj, 'repeat');
					context.fillStyle = pattern;
					context.fill();
									
					context.lineWith = 1;
					context.strokeStyle = 'black';

					context.stroke();
					
					if(botenArray[j*${player1.bord.bordBreedte}+i]===true){
						drawBoats(newX1,newY1,tileWidthOtherBoard,tileHeightOtherBoard);}
					
					
					startPositionsOfTilesX.push(newX1);
					startPositionsOfTilesY.push(newY1);
					
				}
			}}
			
				<!-- fills the tiles where boats are located-->
		function drawBoats(newX1,newY1,tilewidthOwnBoard,tileHeightOwnBoard){
		context.beginPath();
		context.fillStyle='red';
		context.fillRect(newX1 ,newY1, tileWidthOwnBoard, tileHeightOwnBoard);
		context.stroke();
		}
		
		function drawBoatsOther(newX1,newY1,tilewidthOwnBoard,tileHeightOwnBoard){
		context1.beginPath();
		context1.fillStyle='red';
		context1.fillRect(newX1 ,newY1, tileWidthOtherBoard, tileHeightOtherBoard);
		context1.stroke();
		}
		
			<!-- Takes care of the onclick event on the board-->
		function onClick(evt) { 
			var mousePosition = getMousePos(canvas1, evt);
			
			var x = Math.floor(mousePosition.x/tileWidthOtherBoard);
			var y = Math.floor(mousePosition.y/tileHeightOtherBoard);
			
			if(x<tableColumns&&y<tableRows){
			
			$.post('shoot', {x:x, y:y}, 
				function(data){ updateUpperField(data);
				updateLowerField();}
					);
			
			
			}
			else{
			alert('You clicked outside of the field')}

		}
		
		function updateLowerField(){
		$.get('getComputer',function(data){updateLowerField(data)});
		}
		
		function updateUpperField(player1){
		var vakjesArray = [];
		vakjesArray =$(player1.vakjeArray);
		
		
		for (var i = 0; i < tableColumns; i++) {
				for (var j = 0; j < tableRows; j++) {
					
					var newX1 =(j * tileWidthOwnBoard);
					var newY1=( i * tileHeightOwnBoard);
					
					if(vakjesArray[i*tableColumns+j]){
					context.beginPath();
					context.rect(newX1 ,newY1, tileWidthOwnBoard, tileHeightOwnBoard);
					
					context.fillStyle = 'white';
					context.fill();
					
					context.stroke();
					}
					
		
		
		}}}
		
		canvas1.onclick = onClick;

		<!-- Returns the mouse position at the time of the click-->
		function getMousePos(canvas1, evt) {
			var rect = canvas1.getBoundingClientRect();
			return {
				x : evt.clientX - rect.left,
				y : evt.clientY - rect.top
			};
		}

		
		
</script>

</body>
</html>