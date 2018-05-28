var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	var link = function(ctx, x, y){
	    ctx.moveTo(x,y);
	    ctx.lineTo(x+15,y);

	    ctx.moveTo(x+15,y);
	    ctx.lineTo(x+15,y+25);

	    ctx.moveTo(x,y+50);
	    ctx.lineTo(x+15,y+50);

	    ctx.moveTo(x+15,y+50);
	    ctx.lineTo(x+15,y+25);

	    ctx.moveTo(x+15,y+25);
	    ctx.lineTo(x+30,y+25);
	    ctx.stroke();
	}

	var rectangle = function(ctx, x, y){
		ctx.rect(x, y, 220, 40);
		ctx.stroke();
	}

	rectX = 20;
	rectY = 20;

	for(index = 1; index < 9; index ++){
		if(index % 2 == 0){
			rectY += 50;
			rectangle(ctx, rectX, rectY);
		} else{
			if(index != 1){
				rectY += 100;
			}
			rectangle(ctx, rectX, rectY);
		}
	}

	linkX = 240;
	linkY = 40;

	for(index = 1; index < 5; index ++){
		link(ctx, linkX, linkY);
		linkY += 150;
	}