$("#registerform").submit(function() {
	$.post("Register", {
		username: $("#username").val(),
		email: $("#email").val(),
		password: $("#password").val(), 
		passwordconfirm: $("#passwordconfirm").val(),
		checkcode: $("#checkcode").val()
	}, function(data) {
		solve(data);
	});
	return false;
});

function solve(data) {
	if(data.status == "success") {
		selfAlert(data.text);
		location.href = "/BookStore";
	}
	else {
		if(typeof(data.text) == "undefined") selfAlert("出错了 刷新试试~");
		else selfAlert(data.text);
		freshImg();
	}
}

function freshImg() {
	//为啥$（"img"）[0]选择失败
	$("#codeimg").attr("src", "Checkcode?" + Math.random());
	$("#checkcode").val("");
};

$("#codeimg").click(function() {
	freshImg();
});
