package com.danil.task.mapstruct;


import com.danil.task.model.Transaction;
import com.danil.task.model.TransactionBody;

//@Mapper(componentModel = "spring")
public interface TransactionToTransactionBodyMapper {
	
	//TransactionBody toTransactionBody(Transaction transaction);
	
	public default TransactionBody toTransactionBodyMock(final Transaction transaction){
		return new TransactionBody(transaction.getType(), transaction.getAmount());
	}
}
