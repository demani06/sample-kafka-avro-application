package com.deepak.sample.kafka.utils;

import com.deepak.sample.kafka.producer.SampleJsonProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

public class Utils {

    private final static Logger log = Logger.getLogger(SampleJsonProducer.class.getName());

    public static String getJsonAsString(Object object) {

        String json = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warning("json processing failed");
        }

        return json;
    }
}
