
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
				html += '<div class="col-md-6">'+
						'	 <article class="story item" id="story_${item.id}">'+
					    '		<a href="'+ BASE +'/s/' + item.id +'">'+
					    '  		<div class="image" style="background-image: url(${cdn_url('+ item.cover +')});">'+
					    '    		<h3 class="text">'+ item.title + '</h3>'+
					    '    		<div class="mask"></div>'+
					    '  		</div>'+
						'		</a>'+
						'	</article>'+
						'</div>';
			}
			$("div#specials").html(html);
		}
	});
}
