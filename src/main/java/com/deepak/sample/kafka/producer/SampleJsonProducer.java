package com.deepak.sample.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.logging.Logger;

public class SampleJsonProducer {

    private final static Logger log = Logger.getLogger(SampleJsonProducer.class.getName());

    public static void main(String[] args) {

        Properties properties = new Properties();

        // kafka bootstrap server
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String, String> producer = new KafkaProducer<>(properties);

        log.info("producer created");
        // we are going to test different scenarios to illustrate the join

        ProducerRecord<String, String> record= new ProducerRecord<>("string_topic", "Hello World String");
        producer.send(record);

        log.info("message published to topic :"+ record.topic());

        //Flush and close the producer
        producer.flush();
        producer.close();

    }
}
