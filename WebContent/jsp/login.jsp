<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户登录</title>
  </head>
  <body>
  <jsp:include page="base/navigation.jsp"></jsp:include>
  <canvas class="top-float" id="canvas" style="width:100%; high:100%"></canvas>
  	<div class="center-text"><h1>用户登录</h1></div>
	<form id="loginform">
  		<div class="modal-dialog" style="width:30%">
    		<label>用户名</label>
    		<input type="text" class="form-control" id="username" placeholder="用户名" data-warnning="请填写用户名">
    		<div></div>
  		</div>
  		<div class="modal-dialog" style="width:30%">
    		<label>密码</label>
    		<input type="password" class="form-control" id="password" placeholder="密码" data-warnning="请填写密码">
    		<div></div>
  		</div>
  		<div class="modal-dialog" style="width:30%">
  			<button type="submit" class="btn btn-primary" id="submit">提交</button>
  		</div>
  	</form>
  	<div class="modal-dialog" style="width:30%">
  	<a href="forgotpassword.jsp">忘记用户名/密码</a><br>
 	<a href="register.jsp">还没有帐号?点击注册</a>
 	</div>
  <script type="text/javascript" src="/BookStore/js/login.js"></script>
  <script type="text/javascript" src="/BookStore/js/bgimage.js"></script>
  </body>
</html>