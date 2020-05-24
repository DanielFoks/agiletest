package com.danil.task.service;

import java.math.BigDecimal;
import java.util.List;

import com.danil.task.model.Transaction;
import com.danil.task.model.TransactionBody;
import com.danil.task.model.TxType;

public interface UserAmountServiceI {
	List<TransactionBody> getTransactions();
	
	boolean writeAmount(TransactionBody transactionBody);
	
	TransactionBody findById(Integer id);
}
