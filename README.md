# sample-kafka-avro-application

## Prerequisites
1. Install Zookeeper (direct installation or using Docker)
1. Install Kafka (direct installation or using Docker)

## For Json message producer to work  create the below topic 
`./kafka-topics.bat --create --topic string_topic --bootstrap-server localhost:9092`

## For Avro message producer and consumer to work,  create the below topic 
`./kafka-topics.bat --create --topic avro_topic --bootstrap-server localhost:9092`

## Steps to run the Avro publisher and consumer 

1. Run the mvn command to generate the sources from Avro schema- `mvn clean install` This would generate the Student schema class in the target folder
1. Run the SampleAvroProducer class which publishes an avro message to the topic
1. Run the SampleAvroConsumer class which consumes that avro message from the topic and prints it


