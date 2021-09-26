package com.jdutton.pocs.kafka.analysis.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@EnableKafkaStreams
public class KafkaConfiguration {
	
	@Bean
	KafkaStreamsConfiguration defaultKafkaStreamsConfig() {
		Map<String, Object> configProps = new HashMap<>();

		configProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		configProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
		configProps.put(StreamsConfig.APPLICATION_ID_CONFIG, "analysis-service");
		configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, JsonSerializer.class);
		configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

		return new KafkaStreamsConfiguration(configProps);
	}
	
}
