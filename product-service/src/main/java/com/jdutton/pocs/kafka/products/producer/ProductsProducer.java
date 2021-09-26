package com.jdutton.pocs.kafka.products.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.jdutton.pocs.kafka.products.dto.SoldProduct;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductsProducer {
	
	private final KafkaTemplate<String, SoldProduct> soldProductsKafkaTemplate;
	
	public void send(SoldProduct soldProduct) {
		// receives the topic name and the object to send
		this.soldProductsKafkaTemplate.send("sold-products", soldProduct);
	}
}
