

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

// 添加文章
function add_article(){
	var formData = $('#add_article_form').serialize();
	$.post(BASE + '/write', formData, function(response){
		if(response){
			 if(response.status == 200){
				 alertOk("文章发布成功！");
			 } else {
				 alertError(response.msg);
			 }
		}
	});
	return false;
}
