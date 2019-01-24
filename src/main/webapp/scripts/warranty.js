var laws = [];

function updatelaws() {
	var field = document.getElementById("lawsfield");
	field.value = laws.toString();
}

function removediv(div) {
	div.remove();
	removeid(div.id);
	updatelaws();
}

function removeid(num) {
	var index = laws.indexOf(num);
	if (index > -1) {
		laws.splice(index, 1);
	}
}

function addItem() {
	var inputfield = document.getElementById("lawselect");
	fetchlaw(inputfield.options[inputfield.selectedIndex].value);
}

function fetchlaw(id) {
	$.ajax({
		url : 'warranty/admin/fetch.do',
		type : 'GET',
		data : {
			lawid : id
		},
		complete : function(response) {
			var lawstring = response.responseText;
			var law = lawstring.split('&&');
			if (id != 0) {
				$('#lawscontainer').append(
						'<div class="list-item" id="' + law[0] + '" onclick="removediv(this)">' + '<div><b>' + law[1] + '</b></div><div>' + law[2] + '</div></div>');
				laws.push(id);
				updatelaws();
			}
		}
	});
}

$(document).ready(function() {

	var loadedlaws = document.getElementById("lawsfield").value.split(',');
	laws = laws.concat(loadedlaws);

	function fetchlaw(id) {
		$.ajax({
			url : 'messaging/fetch.do',
			type : 'GET',
			data : {
				lawid : id
			},
			complete : function(response) {
				var lawstring = response.responseText;
				var law = lawstring.split('&&');
				if (id != 0) {
					$('#lawscontainer').append('<div class="list-item" id="' + law[0] + '">' + '<div><b>' + law[1] + '</b></div><div>' + law[2] + '</div></div>');
					laws.push(id);
					updatelaws();
					var newlaw = document.getElementById(law[0]);
					newlaw.addEventListener("click", function(e) {
						newlaw.remove();
						removeid(id);
						updatelaws();
					});
				}
			}
		});
	}
});
