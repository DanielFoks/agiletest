package com.danil.task.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAmount {
	private BigDecimal amount;
	
	final ReadWriteLock lock = new ReentrantReadWriteLock();
	
	final List<Transaction> transactionsHistory = new CopyOnWriteArrayList<>();
}
