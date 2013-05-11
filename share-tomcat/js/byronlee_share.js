function checklogin(){
       		if ($('.username').val() == "")
               {        			
       			apprise('亲,你还没有填写用户名哦!');    
       		    setTimeout(del_alert_massege,700);
                   return false;
               }
               if ($('.password').val() == "")
               {              	
               	apprise('亲,你忘记填写密码了!');  
                setTimeout(del_alert_massege,700);
                return false;
              
               }
           }

$(document).ready(function(){
	$(".login_form_1").live("submit",function(txt){
		var options = { 
				success:function(txt){	
				
					   if(txt==1){
						    
						 
						   window.location.href="ShowFiles?access=public";
			                 
					   }
					   else if(txt==2){
						   
						   apprise("用户名或密码错误");
						 
						   
					   
					
					   }
				}
		};
		
		if ($('.username').val() == "")
        {        			
			apprise('亲,你还没有填写用户名哦!');    
		    setTimeout(del_alert_massege,700);
            return false;
        }
        if ($('.password').val() == "")
        {              	
        	apprise('亲,你忘记填写密码了!');  
         setTimeout(del_alert_massege,700);
         return false;
       
        }else{
        	
        	 $(this).ajaxSubmit(options);
			return false;
        }
		
	});
	
});


//删除弹出层
function del_alert_massege(){
	$('.appriseOverlay').remove();
	$('.appriseOuter').remove();
}
function register(){
	window.location.href="register.html";
}
function login(){
	window.location.href="login.jsp";
}
function out(){
	apprise('确定退出该系统？', {'confirm':true},function(r){		
		if(r){					
		  window.location.href="Logout";
			return false;
		}	else{
			return false;
		}
	});	
}
//获取当前时间：
function nowtime()
	{
	var now = new Date();  
	var month =now.getMonth()+1;  
	var day = now.getDate();  
	var hour = now.getHours();  
	var minute  = now.getMinutes();  
	var second = now.getSeconds();  
		$(".ntime").html("当前时间：2012-"+month+"-"+day+" "+hour+":"+minute+":"+second);
}


var Box= new Object()|| {};
Box.start=function(){
 var	width=$(document).width();
 var	height=$(document).height();
 var    left=( $(window).width()) /(3)+"px";
 var    top=( $(window).height() ) / 4 + "px";
 var html="<div class='box' style='background: transparent; position: fixed;top: 0;left: 0;width:"+width+"px;height:"+height+"px;'>"+  
          "<div class='box_content' style='position: relative;left:"+left+";top:"+top+";'    >"+
          "<div class='title'> 文件上传&nbsp;&nbsp;<span style='font-size:12px'>(单次文件大小不超过20M)</span>"+
           "<a href='javascript:void(0)' class='t_delet' onclick='Box.delTypeBox()'> </a></div>"+
          
          
         " <form action='CommonsUploadFile' method='post' id='upload-form' enctype='multipart/form-data' onSubmit='return validateNull()'>"+


         "<div class='shangchuanbnP'><input type='file' hidefoucs='true' name='pic' ></div>"+
          "<input type='radio' style='margin-left: 80px;' name='access' value='1' checked />private"+
          "<input type='radio' name='access' value='2' />public"+
          "<input type='radio' name='access' value='3' />both<br /><br />"+
          "<input type='submit' class='up-button' value='upload' />"+
          "</form>"+
          
          

          
          "</div>"+	 
        "</div>";
 
 $('.box').fadeIn(30);

 $('body').append(html);

};

Box.delTypeBox=function(){
	$('.box').remove();
	$('.box_content').remove();
	
};	




function validateNull(){
    if($("input[name=pic]").val()==""){
 	   apprise("为选择任何文件!");
 	  setTimeout(del_alert_massege,800);
 	   return false;
    }
	
}


// 回收文件
function torecycle(id,o){
	apprise('确定放置回收站？', {'confirm':true},function(r){		
		if(r){					
		 $.post("Delete?id="+id,{id:id},function(txt){
			if(txt==1){
			 //apprise("成功放置回收站!");
			 $(o).parent().parent().slideUp(500);
			}
			else{
				apprise("操作失败!");
				setTimeout(del_alert_massege,700);
			}
			 
		 });
			return false;
		}	else{
			return false;
		}
	});	
	
}
// 恢复文件

function toCycle(id,o){
	apprise('确定恢复该文件？', {'confirm':true},function(r){		
		if(r){					
		 $.post("Delete",{id:id,type:"cycle"},function(txt){
			if(txt==1){
		
			 $(o).parent().parent().slideUp(500);
			}
			else{
				apprise("操作失败!");
				setTimeout(del_alert_massege,700);
			}
			 
		 });
			return false;
		}	else{
			return false;
		}
	});	
	
}


// 彻底删除

function completeDelete(id,o){
	apprise('彻底删除后将不能恢复,确定删除?', {'confirm':true},function(r){		
		if(r){					
		 $.post("Delete",{id:id,type:"complete"},function(txt){
			if(txt==1){
			 $(o).parent().parent().slideUp(500);
			}
			else{
				apprise("操作失败!");
				setTimeout(del_alert_massege,700);
               
			}
			 
		 });
			return false;
		}	else{
			return false;
		}
	});	
	
	
}





// 异步提交文件
$(document).ready(function(){
	$("#upload-form").live("submit",function(txt){
		var options = { 
				success:function(txt){	
				
					   if(txt==1){
						    
						    apprise("文件超过20M!");
						    setTimeout(del_alert_massege,700);
			                 
					   }
					   else if(txt==2){
						   
						   alert("文件上传成功!");
						   window.location.reload();
						   
					   }
					   else{
						   apprise("上传失败!");
						   setTimeout(del_alert_massege,700);
		                  
					   }
				}
		};
		 $(this).ajaxSubmit(options);
		return false;
		
		
	});
	
});
 

/*------------register-------------------*/


//异步注册
$(document).ready(function(){
	$("#login_form").live("submit",function(txt){
		var options = { 
				success:function(txt){	
				
					   if(txt==1){
						    
						   alert("注册成功,点击确定为你跳转到登录界面!");
						   window.location.href="login.jsp";
			                 
					   }
					   else if(txt==2){
						   
						   apprise("注册失败!");
						 
						   
					   
					
					   }
				}
		};
		
		if ($('.user').val() == "")
	    {        			
			apprise('用户名不能为空!');    
		    setTimeout(del_alert_massege,700);
	        return false;
	    }
		else  if ($('.pass').val() == "")
	    {              	
	    	apprise('密码不能为空!');  
	     setTimeout(del_alert_massege,700);
	     return false;
	   
	    }
		else  if ($('.repass').val() == "")
	    {              	
		     apprise('确认密码不能为空!');  
		     setTimeout(del_alert_massege,700);
		     return false;
		   
		    }
		else  if($('.pass').val()!=$('.repass').val()){
  	      apprise('两次密码不一致!');  
 	      setTimeout(del_alert_massege,700);
 	     return false;
 }
		else{
			 $(this).ajaxSubmit(options);
				return false;
		}
		
	});
	
});

