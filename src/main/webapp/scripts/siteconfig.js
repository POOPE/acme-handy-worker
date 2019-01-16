var spamwords = [];

function removespam(div) {
	removespamword(div.id);
	div.remove();
	updatespam();
}

function removespamword(word) {
	var index = spamwords.indexOf(word);
	if (index > -1) {
		spamwords.splice(index, 1);
	}
}

function updatespam() {
	var stringlist = "";
	for ( var i = 0; i < spamwords.length; i++) {
		stringlist = stringlist + spamwords[i];
		if (i < spamwords.length - 1) {
			stringlist = stringlist + ",";
		}
	}
	document.getElementById("spamwordsfield").value = stringlist;
}

function addItem() {
	var word = spamwordinput.value;
	spamwords.push(word);
	updatespam();
	$('#spamwordlist').append('<div class="list-item" id="' + word + '">' + word + '&nbsp;<i  class="fa fa-times" aria-hidden="true"></i></div>');
	var newspam = document.getElementById(word);
	newspam.addEventListener("click", function(e) {
		removespam(this);
	});
}

$(document).ready(function() {
	spamwords = document.getElementById("spamwordsfield").value.split(",");
	var spamwordinput = document.getElementById("spamwordinput");

	function addItem() {
		var word = spamwordinput.value;
		$('#spamwordlist').append('<div id="' + word + '">' + word + '&nbsp;<i  class="fa fa-times" aria-hidden="true"></i></div>');
		var newspam = document.getElementById(word);
		newspam.addEventListener("click", function(e) {
			removespam(this);
		});
	}
});
