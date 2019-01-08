$("#savebutton").click(function(e) {
	$.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		url : 'finder/fetch.do',
		type : 'post',
		data : {
			finder : $(this).serialize()
		},
		complete : function() {
			alert('success');
		}
	});
});

$(document).ready(function($) {
	$(document).on('submit', '#finderform', function(event) {
		event.preventDefault();

		alert('page did not reload');
	});
});
