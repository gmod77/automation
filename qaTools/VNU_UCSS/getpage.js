var page = require('webpage').create(),
	system = require('system');

if (system.args.length === 1) {
	console.log('usage: phantomjs getpage.js <url>');
	phantom.exit();
}

var url = system.args[1];

page.open(url,function(status) {
	if (status !== 'success') {
		console.log('Unable to access');
	} else {
		console.log(page.content);
	}
	phantom.exit();
});