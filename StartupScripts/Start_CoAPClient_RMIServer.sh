cd /home/pi/thingHUB/apache-tomcat-8.0.24/webapps/IOT/WEB-INF/classes
sudo java -classpath CoAPClient_RMIServer.jar:. -D ReceiveMessageInterface.CoAPClient_RMIServer >> abc.txt
