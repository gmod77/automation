#!/bin/bash
# Requires phantomjs to be installed and VNU jar
# VNU: https://bitbucket.org/sideshowbarker/vnu
# phantomJS: http://phantomjs.org/

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

echo "Running command on URL> " $URL
$PHANTOM_DIR/phantomjs getpage.js $URL | java -Dnu.validator.client.out=text -Dnu.validator.client.quiet=yes -jar $VNU_JAR_LOCATION
exit 0