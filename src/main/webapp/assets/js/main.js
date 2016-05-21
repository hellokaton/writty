
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

function alertOk(msg, callback){
	swal({
		title:"提示信息", 
		text: msg, 
		type:"success",
		timer: 3000
	}, callback);
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
	$('.specials').waitMe({
		effect : 'stretch',
		text : '',
		//bg : 'rgb(73,74,95)',
		color : '#000',
		maxSize : '',
		source : ''
	});
	$.get(BASE + '/api/specials/random', {}, function(response){
		if(response && response.specials){
			var html = '';
			var data = response.specials;
			for(i in data){
				$('.specials figure:eq('+ i +')').find('img').attr('src', data[i].cover);
				$('.specials figure:eq('+ i +')').find('h2').text(data[i].title);
				$('.specials figure:eq('+ i +')').find('p').text(data[i].description);
				$('.specials figure:eq('+ i +')').find('a').attr('href', BASE +'/s/' + data[i].id);
			}
			$('.specials').waitMe('hide');
		}
	});
}

var user = {};
//修改密码
user.update_pwd = function(){
	var formData = $('#pwd_form').serialize();
	$.post(BASE + '/reset_pwd', formData, function(response){
		if(response){
			if(response.status == 200){
				alertOk("密码修改成功!", function(){
					window.location.reload();
				});	
			} else if(response.status == 401){
				go_signin();
			} else{
				alertError(response.msg);
			}
		}
	});
	return false;
}