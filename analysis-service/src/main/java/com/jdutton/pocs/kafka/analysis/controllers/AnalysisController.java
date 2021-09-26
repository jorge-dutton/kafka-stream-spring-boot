package com.jdutton.pocs.kafka.analysis.controllers;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdutton.pocs.kafka.analysis.processor.ProductStreamProcessor;

@RestController
public class AnalysisController {
	
	@GetMapping
	public Map<Integer, BigDecimal> getTotalPrice() {
		return ProductStreamProcessor.products;
	}

}
