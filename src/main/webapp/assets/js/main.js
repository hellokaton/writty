
function alertError(msg){
	swal({
		title:"提示信息", 
		text: msg, 
		type:"error",
		timer: 3000
	});
}

function alertOk(msg){
	swal({
		title:"提示信息", 
		text: msg, 
		type:"success",
		timer: 3000
	});
}

function len(o){  
   var n, count = 0;  
   for(n in o){  
      if(o.hasOwnProperty(n)){  
         count++;  
      }  
   }  
   return count;
}  

// 随机专栏
function random_specials(){
	$.get(BASE + '/api/specials/random', {}, function(response){
		if(response && response.specials){
			var html = '';
			var data = response.specials;
			for(i in data){
				var item = data[i];
				html += '<div class="col-sm-6 col-md-3">'+
						'	<div class="portlet">'+
						'		<a href="'+ BASE +'/s/' + item.id +'" style="color: white;">'+
						'			<div class="panel special-row" style="background-image: url('+ item.cover +'); background-size: cover;">'+
						'			</div>'+
						'		</a>'+
						'		<div style="height: 20px; line-height: 1px; margin-top: -10px; margin-bottom: 10px;">'+
						'		<a href="'+ BASE +'/s/' + item.id +'"><h4>'+ item.title + '</h4></a>'+
						'		</div>'+
						'	</div>'+
						'</div>';
			}
			$("div#specials").html(html);
		}
	});
}
