#!/bin/ksh
###################################################################################
#
# This script installs a new MGB war file by restarting 
# the tomcat running under the a223prod user
#
###################################################################################

if [ $(id | grep -c a223prod) -le 0 ]
then
   echo "Script must be run under the a223prod user"
   exit
fi
if [ x$JAVA_HOME = x ]
then
   echo "JAVA_HOME must be set"
   exit
fi

MGB_TOMCAT_HOME=/p/a223/sp1e/mgb/server/apache-tomcat-7.0.16
MGB_DATA=/p/a223/sp1e/mgb/mgbdata/mgbPrd

set -e
set -x

cd $MGB_TOMCAT_HOME/bin
shutdown.sh

sleep 60

cd $MGB_DATA/cache/application
rm *.cache

cd $MGB_TOMCAT_HOME/webapps
cp -p mgbPrd/WEB-INF/classes/hibernate.properties ./mgbPrd.hibernate.properties
rm -r mgbPrd

mv mgbPrd.war mgbPrd.war.old

cp /tmp/mgbPrd.war mgbPrd.war

# Extract WAR file manually to be able to install hibernate.properties;
# makes the 'chmod' command easier now too.
mkdir mgbPrd
cd mgbPrd
$JAVA_HOME/bin/jar xf ../mgbPrd.war
rm WEB-INF/classes/hibernate.properties
mv ../mgbPrd.hibernate.properties WEB-INF/classes/hibernate.properties
chmod 600 WEB-INF/classes/hibernate.properties


cd $MGB_TOMCAT_HOME/bin
startup.sh

sleep 60

set +x

echo "run http://mgb.westlb.sko.de:8092/mgbPrd in the IE to verify installation"

####################################################################################
# extra steps
####################################################################################
#mv /p/a223/sp1e/mgb/mgbdata/mgbPrd/scripts/import_BBM.ctl /p/a223/sp1e/mgb/mgbdata/mgbPrd/scripts/import_BBM.ctl.old
#cp /tmp/import_BBM.ctl /p/a223/sp1e/mgb/mgbdata/mgbPrd/scripts/import_BBM.ctl
