package com.jdutton.pocs.kafka.analysis.processor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.jdutton.pocs.kafka.analysis.dto.SoldProduct;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductStreamProcessor {

	public static Map<Integer, BigDecimal> products = new HashMap<>();

	@Autowired
	public void process(StreamsBuilder streamBuilder) {
		Serde<String> keySerde = Serdes.String();

		Serde<SoldProduct> valueSerde = new JsonSerde<>(SoldProduct.class);

		streamBuilder.stream("sold-products", Consumed.with(keySerde, valueSerde))
				.map((key, value) -> new KeyValue<>(value.getId(), value)).foreach(this::add);
	}

	private void add(Integer key, SoldProduct soldProduct) {
		products.put(key, products.getOrDefault(key, BigDecimal.ZERO).add(soldProduct.getPrice()));
	}
}
