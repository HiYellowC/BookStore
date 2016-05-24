$("#logout").click(function() {
	$.post("Logout", {}, function() {
		location.href = "/BookStore";
	});
		return false;
});
