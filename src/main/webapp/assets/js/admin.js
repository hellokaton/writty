

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

// 创建专栏
function add_specials(){
	var formData = $('#add_specials_form').serialize();
	$.post(BASE + '/admin/specials', formData, function(response){
		if(response){
			 if(response.status == 200){
				 alertOk("修改成功！");
			 } else {
				 alertError(response.msg);
			 }
		}
	});
	return false;
}

// 编辑专栏
function edit_specials(){
	var formData = $('#edit_specials_form').serialize();
	$.post(BASE + '/admin/specials/update', formData, function(response){
		if(response){
			 if(response.status == 200){
				 alertOk("修改成功！");
			 } else {
				 alertError(response.msg);
			 }
		}
	});
	return false;
}
