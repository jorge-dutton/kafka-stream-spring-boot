package com.jdutton.pocs.kafka.products.job;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.jdutton.pocs.kafka.products.dto.SoldProduct;
import com.jdutton.pocs.kafka.products.producer.ProductsProducer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledTestProducer {

	private final ProductsProducer productsProducer;

	@Scheduled(fixedDelay = 2000)
	public void testProducer() {
		List<SoldProduct> products = Arrays.asList(
				SoldProduct.builder().id(1).name("Prod1").price(new BigDecimal(100.23)).build(),
				SoldProduct.builder().id(2).name("Prod2").price(new BigDecimal(10.23)).build(),
				SoldProduct.builder().id(3).name("Prod3").price(new BigDecimal(77.23)).build());
		
		int index = ThreadLocalRandom.current().nextInt(0, products.size());
		productsProducer.send(products.get(index));
	}
}
