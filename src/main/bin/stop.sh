#!/bin/bash

get_pid() {	
	STR=$1
	PID=$2
    if [ ! -z "$PID" ]; then
    	JAVA_PID=`ps -ef|grep "$STR"|grep "$PID"|grep -v grep|awk '{print $2}'`
	else
		JAVA_PID=`ps -ef|grep "$STR"|grep -v grep|awk '{print $2}'`
    fi
    echo $JAVA_PID;
}

base=`dirname $0`/..
pidfile=$base/bin/sync.pid
if [ ! -f "$pidfile" ];then
	echo "youzan_crawl is not running. exists"
	exit
fi

pid=`cat $pidfile`
if [ "$pid" == "" ] ; then
	pid=`get_pid "appName=sync"`
fi

echo -e "`hostname`: stopping youzan_crawl $pid ... "
kill $pid

LOOPS=0
while (true); 
do 
	gpid=`get_pid "appName=youzan_crawl" "$pid"`
    if [ "$gpid" == "" ] ; then
    	echo "Oook! cost:$LOOPS"
    	`rm $pidfile`
    	break;
    fi
    let LOOPS=LOOPS+1
    sleep 1
done
