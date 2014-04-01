#!/bin/bash
# Requires phantomjs and ucss installed
# UCSS: https://github.com/operasoftware/ucss
# phantomJS: http://phantomjs.org/
# 
# Runs phantomjs on a url to get the css files
# Then runs ucss comparing the css file to the url used


# Set your phantomjs directory
PHANTOM_DIR=/usr/share

if [ -url = "$1" ]
then
    URL=$2
else
    echo "-url <url> is required"
    exit 1
fi

echo "Running suite against> " $URL
for i in `$PHANTOM_DIR/phantomjs getpagecss.js $URL`;
do
    echo $i
    ucss -h $URL -c $i
    echo  
done
