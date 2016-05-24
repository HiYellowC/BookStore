$("#loginform").submit(function() {
	$.post("Login", {
		username: $("#username").val(),
		password: $("#password").val()
	}, function(data) {
		solve(data);
	});
	return false;
});

function solve(data) {
	if(data.status == "success") {
		location.href = "/BookStore";
	}
	else {
		selfAlert("用户名或密码错误！");
	}
}