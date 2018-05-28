Korea Fine Dust Tracking Project
===================


This is a project to practice how to use spring framework and swagger framework.




What you need
-------------

1. A public data portal service key is required.
2. "flyingdust.config.properties" create a file.
3. Input service.key=[ISSUED-KEY] in file.
 - ex) service.key=abcd1234
4. After the build, enter "java -jar -DFLYINGDUST_HOME=[flyingdust.config.properteis:ROOT_PATH] .\flyingdust-0.0.1.war" as the run command
 - ex) java -jar -DFLYINGDUST_HOME=C:\DEV_HOME\FLYINGDUST_HOME .\flyingdust-0.0.1.war
5. Check in at http://localhost:5555/flyingdust/api 
