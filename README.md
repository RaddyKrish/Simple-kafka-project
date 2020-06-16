# Simple-kafka-project
 A bare-bone kafka producer and consumer application using SpringBoot that could be run locally

Reference: https://kafka.apache.org/quickstart

If using windows all .sh reference above should be replaced with .bat. Also the .bat files will be under bin\windows\

Setup:

complete step 1 to 3 mentioned in https://kafka.apache.org/quickstart
To publish message through this app you will need muti broker cluster. Jump to step 6. Just do the additional broker creation and topic creation. You could Stop at that.
This completes kafka server setup.
As an alternate you could do above steps by following (kafka_setup_commands.txt) 

In your IDE you could create a new maven project using the attached pox.xml
Based on Topic name created you could update the producer and consumer class. The topic name i used was "secondtopic".
