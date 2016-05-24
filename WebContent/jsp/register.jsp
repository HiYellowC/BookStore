<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>用户注册</title>
  </head>
  <body>
  <jsp:include page="base/navigation.jsp"></jsp:include>
  <canvas class="top-float" id="canvas" style="width:100%; high:100%"></canvas>
  	<div class="center-text"><h1>用户注册</h1></div>
	<form id="registerform">
  		<div class="modal-dialog" style="width:30%">
    		<label>用户名</label>
    		<input type="text" class="form-control" id="username" placeholder="用户名" data-warnning="请填写用户名">
  		</div>
  		<div class="modal-dialog" style="width:30%">
    		<label>邮箱地址</label>
    		<input type="text" class="form-control" id="email" placeholder="邮箱地址" data-warnning="请填写正确的邮箱地址">
  		</div>
  		<div class="modal-dialog" style="width:30%">
    		<label>密码</label>
    		<input type="password" class="form-control" id="password" placeholder="密码" data-warnning="请填写密码">
  		</div>
  		<div class="modal-dialog" style="width:30%">
    		<label>确认密码</label>
    		<input type="password" class="form-control" id="passwordconfirm" placeholder="确认密码" data-warnning1="请填写确认密码" data-warnning2="两个密码不一致">
  		</div>
  		<div class="modal-dialog" style="width:30%">
    		<label>验证码</label>
    		<img id="codeimg" src="Checkcode"/>
    		<input type="text" class="form-control" id="checkcode" placeholder="验证码" data-warnning="请填写验证码">
  		</div>
  		<div class="modal-dialog" style="width:30%">
  			<button type="submit" class="btn btn-primary" name="submit">提交</button>
  		</div>
  	</form>
  <script type="text/javascript" src="/BookStore/js/register.js"></script>
  <script type="text/javascript" src="/BookStore/js/bgimage.js"></script>
  </body>
</html>