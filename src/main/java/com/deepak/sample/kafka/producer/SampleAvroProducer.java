package com.deepak.sample.kafka.producer;

import com.deepak.sample.kafka.serialiser.AvroSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.logging.Logger;

@Slf4j
public class SampleAvroProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();

        // kafka bootstrap server
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class.getName());
        Producer<String, com.deepak.sample.kafka.avro.model.Student> producer = new KafkaProducer<>(properties);

        log.info("Avro producer created");

        com.deepak.sample.kafka.avro.model.Student student
                = com.deepak.sample.kafka.avro.model.Student.newBuilder().setStudentId("12246").setStudentName("Harry").setAge(45).build();

        ProducerRecord<String, com.deepak.sample.kafka.avro.model.Student> record = new ProducerRecord<>("avro_topic", "2" ,student);
        producer.send(record);

        log.info("message published to topic :" + record.topic());

        //Flush and close the producer
        producer.flush();
        producer.close();

    }
}
