var terms = [];

function removeterms(div) {
	removeterm(div.id);
	div.remove();
	updateterms();
}

function removeterm(word) {
	var index = terms.indexOf(word);
	if (index > -1) {
		terms.splice(index, 1);
	}
}

function updateterms() {
	var stringlist = "";
	for ( var i = 0; i < terms.length; i++) {
		stringlist = stringlist + terms[i];
		if (i < terms.length - 1) {
			stringlist = stringlist + ",";
		}
	}
	document.getElementById("termsfield").value = stringlist;
}

function addItem() {
	var word = terminput.value;
	terms.push(word);
	updateterms();
	$('#termslist').append('<div class="list-item" id="' + word + '">' + word + '&nbsp;<i  class="fa fa-times" aria-hidden="true"></i></div>');
	var newterm = document.getElementById(word);
	newterm.addEventListener("click", function(e) {
		removeterms(this);
	});
}

$(document).ready(function() {
	terms = document.getElementById("termsfield").value.split(",");
	var terminput = document.getElementById("terminput");

	function addItem() {
		var word = terminput.value;
		$('#spamwordlist').append('<div id="' + word + '">' + word + '&nbsp;<i  class="fa fa-times" aria-hidden="true"></i></div>');
		var newspam = document.getElementById(word);
		newspam.addEventListener("click", function(e) {
			removespam(this);
		});
	}
});
