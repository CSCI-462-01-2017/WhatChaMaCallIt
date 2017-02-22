#!/bin/bash
cd ./openmrs-module-legacyui
echo 'Running MVN CLEAN INSTALL -DMAVEN.TEST.SKIP=TRUE'
mvn clean install -Dmaven.test.skip=true
cd ..
#change the /user/ to your username on your machine
if mv -u ./openmrs-module-legacyui/omod/target/legacyui-1.2.4-SNAPSHOT.omod /home/user/.OpenMRS/modules; then
	echo "Successfully moved module"
	cd ./openmrs-core
	mvn clean install -Dmaven.test.skip=true
	cd ./webapp
	mvn jetty:run
else
	echo "Module failed to move, check pathing"
fi
