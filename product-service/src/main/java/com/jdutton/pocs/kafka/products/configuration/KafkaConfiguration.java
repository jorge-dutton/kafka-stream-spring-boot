package com.jdutton.pocs.kafka.products.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.jdutton.pocs.kafka.products.dto.SoldProduct;

@Configuration
@EnableKafka
public class KafkaConfiguration {

	/**
	 * Creates a topic with name 'sold-products'
	 */
	@Bean
	NewTopic soldProducts() {
		return TopicBuilder.name("sold-products").partitions(6).replicas(1).build();
	}

	@Bean
	public ProducerFactory<String, SoldProduct> soldProductsProducerFactory() {

		Map<String, Object> configProps = new HashMap<>();

		// Kafka broker host
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		// Key serializer
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		// Value serializer
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// Don't add the package name of the value to the headers
		configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	/**
	 * The template responsible of the Kafka communication.
	 * 
	 * This temaplet will beused for the producer component to send events to the
	 * broker
	 * 
	 * @return Kafka template
	 */
	@Bean
	public KafkaTemplate<String, SoldProduct> soldProductsKafkaTemplate() {
		return new KafkaTemplate<>(this.soldProductsProducerFactory());
	}
}
