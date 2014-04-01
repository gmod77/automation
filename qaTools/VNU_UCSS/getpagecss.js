var page = require('webpage').create(),
	system = require('system');

if (system.args.length === 1) {
	console.log('usage: phantomjs getpage.js <url>');
	phantom.exit();
}

var url = system.args[1];

page.open(url,function(status) {
	var links = [];
	if (status !== 'success') {
		console.log('Unable to access');
	} else {
		links = page.evaluate(function() {
			var nodes = [];
			var a = document.head.querySelectorAll("link");
			for (var i = 0; i < a.length; i++) {
				if (a[i].href.match(/^http.*\.css/g)) {
					nodes.push(a[i].href);
				}
			}
			return nodes;
		});
	}

	for (var i = 0; i < links.length; i++) {
		console.log(links[i]);
	}

	phantom.exit();
});