# WhatChaMaCallIt

The following steps are located here: https://wiki.openmrs.org/display/docs/Step+by+Step+Installation+for+Developers
How to run:
Install at least version java 1.6 (the JDK if you plan to edit the source, JRE if you are just running), MySql, Eclipse IDE for Java EE Developers, Tomcat8.x is recommended but optional, then checkout the repo. 
Go to the most outer project directory and run:
```
mvn clean install
```
Ensure you have MySQL running  
Get the latest version of the legacyui module code
```
git clone https://github.com/openmrs/openmrs-module-legacyui.git
```
Build the module
``` 
cd openmrs-module-legacyui
mvn clean install
```
After successfully building the legacyui module, you will find it at openmrs-module-legacyui/omod/target/legacyui-{version}-SNAPSHOT.omod. Move this file to:
```
home/.OpenMRS/modules/
```
If the modules directory is not there, create it. 

Then you can start the OpenMRS Platform as follows: move into the "webapp" directory and execute following command:
```
mvn jetty:run
```
Wait for the [ INFO ] Started Jetty Server message and open the web browser at http://localhost:8080/openmrs. Follow the instructions.
