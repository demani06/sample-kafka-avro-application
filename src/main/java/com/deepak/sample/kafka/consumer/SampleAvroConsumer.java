package com.deepak.sample.kafka.consumer;

import com.deepak.sample.kafka.avro.model.Student;
import com.deepak.sample.kafka.serialiser.AvroDeserializer;
import com.deepak.sample.kafka.serialiser.AvroSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class SampleAvroConsumer {

    public static void main(String[] args) {

        Properties properties = new Properties();

        // kafka bootstrap server
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "avro-consumer");

        Consumer<String, com.deepak.sample.kafka.avro.model.Student> consumer = new KafkaConsumer<>(properties, new StringDeserializer(), new AvroDeserializer<>(Student.class ));

        log.info("Avro consumer created");

        consumer.subscribe(Collections.singletonList("avro_topic"));

        while(true){
            ConsumerRecords<String,Student> records=consumer.poll(100L);
            for(ConsumerRecord<String,Student> record: records){
                log.info("Key: "+ record.key() + ", Value:" +record.value());
                log.info("Partition:" + record.partition()+",Offset:"+record.offset());
            }


        }
    }
}
