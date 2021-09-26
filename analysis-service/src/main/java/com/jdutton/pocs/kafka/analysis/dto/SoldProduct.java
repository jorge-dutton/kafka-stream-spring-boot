package com.jdutton.pocs.kafka.analysis.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SoldProduct implements Serializable {
	
	private static final long serialVersionUID = -5296400678997396189L;
	
	private Integer id;
	private String name;
	private BigDecimal price;
	
}
