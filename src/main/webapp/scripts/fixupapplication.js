$(document).ready(function() {
	$(".ACCEPTED").each(function(index) {

		$(this).parent().parent().css('background-color', '#72db73');

	});

	$(".REJECTED").each(function(index) {

		$(this).parent().parent().css('background-color', '##ff5e19');

	});
});
