<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>忘记密码</title>
  </head>
  <body>
  <jsp:include page="base/navigation.jsp"></jsp:include>
  <canvas class="top-float" id="canvas" style="width:100%; high:100%"></canvas>
  	<div class="center-text">
  		<h1>找回登录信息</h1>
  		<h5>请输入你注册时使用的邮箱地址，系统将自动向你的邮箱发送一封含有您登录信息的电子邮件， 你可以看到你的重置码，并可以重新设置登录密码。</h5>
  	</div>	
	<form id="forgotpasswordform">
  		<div class="modal-dialog" style="width:30%">
    		<label>注册电子邮件地址</label>
    		<input type="text" class="form-control" id="email" placeholder="邮箱地址">
    	</div>
    	<div class="modal-dialog" id="codediv" style="width:30%">
    		<label>验证码</label>
    		<img id="codeimg" src="Checkcode"/>
    		<input type="text" class="form-control" id="checkcode" placeholder="验证码" >
  		</div>
  		<div class="modal-dialog" style="width:30%">
  			<button type="submit" class="btn btn-primary" name="submit">提交</button>
  		</div>
  	</form>
  	<script type="text/javascript" src="/BookStore/js/forgotpassword.js"></script>
  	<script type="text/javascript" src="/BookStore/js/bgimage.js"></script>
  </body>
</html>