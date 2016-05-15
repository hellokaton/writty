

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
						'	<div class="panel special-row">'+
						'		<div class="h4 text-blank"><a href="'+ BASE + '/s/' + item.id +'">'+ item.title +'</a></div>'+
						'		<div class="m-t-20 m-b-10 description">'+
						'			<span class="text-muted">'+ item.description +'</span>'+
						'		</div>'+
						'		<div class="text-right follow">'+ item.post_count +'篇文章，'+ item.follow_count +'关注</div>'+
						'	</div>'+
						'</div>';
			}
			$("div#specials").html(html);
		}
	});
}
