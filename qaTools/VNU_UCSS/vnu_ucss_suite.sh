#!/bin/bash
# Requires phantomjs and UCSS to be installed and access to VNU jar
# VNU: https://bitbucket.org/sideshowbarker/vnu
# UCSS: https://github.com/operasoftware/ucss
# phantomJS: http://phantomjs.org/
#
# Give this a url and phantomJS will extract the rendered page source
# and send it through VNU validator to check the HTML. Then the page will
# be scraped for any CSS files and each file will be compared to the HTML file
# checking for any unused or duplicated CSS.

# You must set the location of phantomJS and VNU jar location
PHANTOM_DIR=/usr/share
VNU_JAR_LOCATION=/usr/share/vnu/vnu.jar


if [ -url = "$1" ]
	then
		URL=$2
	else
		echo "-url <url> is required"
		exit 1
fi

TMPFILE=`mktemp /tmp/UD.XXXXXX`

echo "Retrieving HTML from>" $URL
$PHANTOM_DIR/phantomjs getpage.js $URL > $TMPFILE ; echo

if grep -Fxq "Unable to access" $TMPFILE
	then
	echo "URL invalid or inaccessable"
	rm $TMPFILE
	exit 1
fi

echo "Running VNU Validator"
java -Dnu.validator.client.out=text -Dnu.validator.client.quiet=yes -jar $VNU_JAR_LOCATION $TMPFILE ; echo

echo "Getting CSS files from>" $URL
for i in `$PHANTOM_DIR/phantomjs getpagecss.js $URL`;
	do
	    echo $i
	    ucss -h $TMPFILE -c $i ; echo
	done

rm $TMPFILE

exit 0