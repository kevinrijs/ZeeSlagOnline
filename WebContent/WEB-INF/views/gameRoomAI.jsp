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

<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/3.10.1/lodash.min.js"></script>



<link rel="stylesheet" href="http://localhost:8080/ZeeSlagOnline/resources/style.css">
<title>Game Room</title>

</head>
<body>
<div><h2>Your Board</h2>
<canvas id="canvasOwn" width="${player1.bord.bordBreedte*30}}"
		height="${player1.bord.bordLengte*30}"></canvas>
		</div><div><h2>Opponent's Board</h2>
<canvas id="canvasOther" width="${player1.bord.bordBreedte*50}}"
		height="${player1.bord.bordLengte*50}"></canvas>
		</div>


<script>
$("error");
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
		
		var bord;
		var tegenstander_bord;
			
		
		$(document).ready(function(){
			$.get("getComputer", function(data){
				console.log('getComputer returns: ', data);
				tegenstander_bord = data.bord;
				drawFieldLowerBoard(context1,tableColumns,tableRows);
			});
			$.get('getPlayer',function(data){
				console.log('getPlayer returns: ', data);
				bord = data.bord;
				drawFieldUpperBoard(context, tableColumns, tableRows,startPositionsOfTilesX,startPositionsOfTilesY);
			});
			
			
			
		});
	
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
						
						
						
						/*console.log(i, j, j*${player1.bord.bordBreedte}+i, tegenstander_bord.vakjeArray[j*${player1.bord.bordBreedte}+i]);*/
						if(tegenstander_bord.vakjeArray[j*${player1.bord.bordBreedte}+i].beschoten){
						var color = 'white';
						drawBoatsOther(newX,newY,tileWidthOwnBoard,tileHeightOwnBoard,color);
						}
						
						if(tegenstander_bord.vakjeArray[j*${player1.bord.bordBreedte}+i].bevatBoot && tegenstander_bord.vakjeArray[j*${player1.bord.bordBreedte}+i].beschoten){
						var color = 'red';
							drawBoatsOther(newX,newY,tileWidthOwnBoard,tileHeightOwnBoard,color);
						}
					}
			}
		}
		
				

			<!-- draws the field with the supplied dimensions-->
		function drawFieldUpperBoard(context, tableColumns, tableRows) {
			

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
					
					
						
					if(bord.vakjeArray[j*tableColumns+i].beschoten){
						var color ='white';
						drawBoats(newX1,newY1,tileWidthOtherBoard,tileHeightOtherBoard,color);
					}
					
					if(bord.vakjeArray[j*${player1.bord.bordBreedte}+i].bevatBoot && bord.vakjeArray[j*${player1.bord.bordBreedte}+i].beschoten){
						var color = 'red';
						drawBoats(newX1,newY1,tileWidthOtherBoard,tileHeightOtherBoard,color);
					}
					
					
					
					
				}
			}
		}
			
				<!-- fills the tiles where boats are located-->
		function drawBoats(newX1,newY1,tilewidthOwnBoard,tileHeightOwnBoard,color){
			context.beginPath();
			context.fillStyle=color;
			context.fillRect(newX1 ,newY1, tileWidthOwnBoard, tileHeightOwnBoard);
			context.stroke();
		}
		
		function drawBoatsOther(newX1,newY1,tilewidthOwnBoard,tileHeightOwnBoard,color){
			context1.beginPath();
			context1.fillStyle=color;
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
					function(data){
					updateUpperField(data);
					updateLowerField();
					});

			}
			else{
			alert('You clicked outside of the field')}

		}
		
		function updateLowerField(){
			$.get('getComputer',function(data){
			tegenstander_bord = data.bord;
			drawFieldLowerBoard(context1, tableColumns, tableRows);
			});
		}
		
		function updateUpperField(player1){
			bord=player1.bord;
			drawFieldUpperBoard(context, tableColumns, tableRows);
		}
		
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