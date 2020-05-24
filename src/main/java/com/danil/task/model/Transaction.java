package com.danil.task.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	private Integer id;
	
	private TxType type;
	
	private BigDecimal amount;
	
	private LocalDateTime effectiveDate;
}
