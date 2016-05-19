
function go_signin(){
	swal({
		title:"提示信息", 
		text:"您没有登录，3秒后跳转到登录页面！", 
		type:"warning",
		confirmButtonText:"点击跳转",
		timer: 3000
	},function(isConfirm){
		if (isConfirm) {
			setTimeout(function(){
				window.location.href= BASE + '/signin';
			}, 300);
		} else{
			window.location.href= BASE + '/signin';
		}
	});
}

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
					    '  		<div class="image" style="background-image: url('+ item.cover +');">'+
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
