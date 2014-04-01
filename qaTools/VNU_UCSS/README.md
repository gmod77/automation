# UCSS and VNU validator for geno

## Requirements
- PhantomJS ([www.phantomjs.org](http://phantomjs.org))
- UCSS ([UCSS](https://github.com/operasoftware/ucss))
- VNU Validator ([VNU](https://bitbucket.org/sideshowbarker/vnu))

Install the above applications as per the above site instructions and note the install locations.

## UCSS
Edit runUcss.sh with the correct location of phantomjs

To run: ```./runUcss.sh -url http://www.geno.com```

This will run phantomJS against the given url and pull each CSS file the site has. Then for each CSS file it runs UCSS against the URL.

## VNU Validator
Edit runVNU.sh with the correct location of phantomjs and the location of the vnu.jar

To run: ```./runVNU.sh -url http://www.geno.com```

This will run phantomJS against the given url and grab the full html content. It will then run VNU validator on that content and output put a report.

## UCSS & VNU Suite
Edit vnu_ucss_suite.sh with the correct location of phantomjs

To run: ```./vnu_ucss_suite.sh -url http://www.geno.com```
This will run both VNU and UCSS against the html content of the given url.
