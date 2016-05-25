$("#forgotpasswordform").submit(function() {
	$.post("ForgotPassword", {
		email: $("#email").val(),
		checkcode: $("#checkcode").val()
	}, function(data) {
		solveforgot(data);
	});
	return false;
});

function changesubmit() {
	$("#resetpasswordform").submit(function() {
		$.post("ResetPassword", {
			email: $("#disabledInput").val(),
			resetcode: $("#resetcode").val(),
			password: $("#password").val(),
			confirmpassword: $("#confirmpassword").val()
		}, function(data) {
			solvereset(data);
		});
		return false;
	});
}

function solvereset(data) {
	if(data.status == "success") {
		selfAlert(data.text);
		setTimeout(function() {
			location.href = "/BookStore";
		}, 3000);
	}
	else {
		selfAlert(data.text);
	}
};

function solveforgot(data) {
	if(data.status == "fault") {
		selfAlert(data.text);
		freshImg();
	}
	else {
		var email = $("#email").val();
		changeform(email);
		changesubmit();
	}
}

function changeform(email) {
	var html = "<form id='resetpasswordform'>";
	html += "<div class='modal-dialog' style='width:30%'><label>注册电子邮件地址</label><input class='form-control' id='disabledInput' type='text' placeholder='" + email +"' disabled></div>";
	html += "<div class='modal-dialog' style='width:30%'><label>重置码</label><input type='text' class='form-control' id='resetcode' placeholder='邮件获取的重置码'></div>";
	html += "<div class='modal-dialog' style='width:30%'><label>新的密码</label><input type='password' class='form-control' id='password' placeholder='新的密码'></div>";
	html += "<div class='modal-dialog' style='width:30%'><label>确认新的密码</label><input type='password' class='form-control' id='passwordconfirm' placeholder='确认新的密码'></div>";
	html += "<div class='modal-dialog' style='width:30%'><button type='submit' class='btn btn-primary' name='submit'>提交</button></div>"
	html += "</form>";	
	$("#forgotpasswordform").replaceWith(html);
};

function freshImg() {
	//为啥$（"img"）[0]选择失败
	$("#codeimg").attr("src", "Checkcode?" + Math.random());
	$("#checkcode").val("");
};

$("#codeimg").click(function() {
	freshImg();
});