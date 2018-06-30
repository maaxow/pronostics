angular.module('pronostic.controllers.finales', [])
	.controller("FinaleController", ['$scope', '$game', function($scope, $game){
		
		$scope.huit = [];
		
		$game.getFinaleGame().then(function(response){
			console.log("totto", response);
			for(var index in response.data){
				console.log("vefdedfv", response.data[index]);
				if(response.data[index].team1.group === "W"){
					$scope.huit.push(response.data[index]);
				}
			}
		});
	}]);
//	.controller("FinaleGraphController", ['$scope', function($scope){
//			console.log("FinaleController");
//			
//			var c = document.getElementById("myCanvas");
//			var ctx = c.getContext("2d");
//			var unknownFlag = document.getElementById("unknown");
//			var huitiemeLink = function(ctx, x, y){
//			    ctx.moveTo(x,y);
//			    ctx.lineTo(x+15,y);
//
//			    ctx.moveTo(x+15,y);
//			    ctx.lineTo(x+15,y+25);
//
//			    ctx.moveTo(x,y+50);
//			    ctx.lineTo(x+15,y+50);
//
//			    ctx.moveTo(x+15,y+50);
//			    ctx.lineTo(x+15,y+25);
//
//			    ctx.moveTo(x+15,y+25);
//			    ctx.lineTo(x+30,y+25);
//			    ctx.stroke();
//			}
//
//			var quartLink = function(ctx, x, y){
//			    ctx.moveTo(x,y);
//			    ctx.lineTo(x+15,y);
//
//			    ctx.moveTo(x+15,y);
//			    ctx.lineTo(x+15,y+150);
//
//			    ctx.moveTo(x,y+150);
//			    ctx.lineTo(x+15,y+150);
//
//			    ctx.moveTo(x+15,y+150);
//			    ctx.lineTo(x+15,y+25);
//
//			    ctx.moveTo(x+15,y+75);
//			    ctx.lineTo(x+30,y+75);
//			    ctx.stroke();
//			}
//
//			var demiLink = function(ctx, x, y){
//			    ctx.moveTo(x,y);
//			    ctx.lineTo(x+15,y);
//
//			    ctx.moveTo(x+15,y);
//			    ctx.lineTo(x+15,y+300);
//
//			    ctx.moveTo(x,y+300);
//			    ctx.lineTo(x+15,y+300);
//
//			    ctx.moveTo(x+15,y+300);
//			    ctx.lineTo(x+15,y+25);
//
//			    ctx.moveTo(x+15,y+150);
//			    ctx.lineTo(x+30,y+150);
//			    ctx.stroke();
//			}
//
//			var finaleLink = function(ctx, x, y){
//			    ctx.moveTo(x,y);
//			    ctx.lineTo(x+15,y);
//
//			    ctx.moveTo(x+15,y);
//			    ctx.lineTo(x+15,y+600);
//
//			    ctx.moveTo(x,y+600);
//			    ctx.lineTo(x+15,y+600);
//
//			    ctx.moveTo(x+15,y+600);
//			    ctx.lineTo(x+15,y+25);
//
//			    ctx.moveTo(x+15,y+300);
//			    ctx.lineTo(x+250,y+300);
//			    ctx.stroke();
//			}
//
//			var rectangle = function(ctx, x, y){
//				ctx.rect(x, y, 220, 40);
//				ctx.font = "18px Calibri,Geneva,Arial";
//				ctx.drawImage(unknownFlag, x+6 , y+7, 45, 25);
//				ctx.strokeText("Equipe", x+55, y+25);
//				ctx.strokeText("0", x+205, y+25);
//				ctx.stroke();
//			}
//
//			rectX = 20;
//			rectY = 20;
//			linkX = 240;
//			linkY = 40;
//
//			//rectangles des 8e de finale
//			for(index = 1; index < 17; index ++){
//				if(index % 2 == 0){
//					rectY += 50;
//					rectangle(ctx, rectX, rectY);
//				} else{
//					if(index != 1){
//						rectY += 100;
//					}
//					rectangle(ctx, rectX, rectY);
//				}
//			}
//			//liens des 8e de finales
//			for(index = 1; index < 9; index ++){
//				huitiemeLink(ctx, linkX, linkY);
//				linkY += 150;
//			}
//
//			rectX = 270;
//			rectY = 45;
//			linkX = 490;
//			linkY = 65;
//
//			//rectangles des quarts de finale
//			for(index = 1; index < 9; index ++){
//				rectangle(ctx, rectX, rectY);
//				rectY += 150;
//			}
//			//liens des quarts de finale
//			for(index = 1; index < 5; index ++){
//				quartLink(ctx, linkX, linkY);
//				linkY += 300;
//			}
//
//				rectX = 520;
//				rectY = 120;
//				linkX = 740;
//				linkY = 140;
//
//			//rectangles des quarts de finale
//			for(index = 1; index < 5; index ++){
//				rectangle(ctx, rectX, rectY);
//				rectY += 300;
//			}
//			//liens des quarts de finale
//			for(index = 1; index < 3; index ++){
//				demiLink(ctx, linkX, linkY);
//				linkY += 600;
//			}
//
//				rectX = 770;
//				rectY = 270;
//				linkX = 740;
//				linkY = 140;
//
//			//rectangles des quarts de finale
//			for(index = 1; index < 3; index ++){
//				rectangle(ctx, rectX, rectY);
//				rectY += 600;
//			}
//			//liens des quarts de finale
//		/*	for(index = 1; index < 3; index ++){*/
//				finaleLink(ctx, 990, 290);
//				/*linkY += 600;
//			}*/
//
//			
//			
//}]);

