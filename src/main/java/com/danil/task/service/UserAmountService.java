package com.danil.task.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.danil.task.exception.NotEnoughMoneyException;
import com.danil.task.mapstruct.TransactionToTransactionBodyMapper;
import com.danil.task.model.Transaction;
import com.danil.task.model.TransactionBody;
import com.danil.task.model.TxType;
import com.danil.task.model.UserAmount;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAmountService implements UserAmountServiceI, TransactionToTransactionBodyMapper {
	
	private final UserAmount userAmount;
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	public UserAmountService(final UserAmount userAmount,
							 final SimpMessagingTemplate template) {
		this.userAmount = userAmount;
		this.template = template;
	}
	
	@Override
	public List<TransactionBody> getTransactions() {
		return userAmount.getTransactionsHistory().stream().map(this::toTransactionBodyMock).collect(Collectors.toList());
	}
	
	@Override
	public boolean writeAmount(final TransactionBody transactionBody) {
		userAmount.getLock().writeLock().lock();
		
		final BigDecimal currentAmount = userAmount.getAmount();
		final BigDecimal amount = transactionBody.getAmount();
		final TxType type = transactionBody.getTxType();
		final Transaction transaction = new Transaction();
		try {
			switch (type) {
				case DEBIT:
					userAmount.setAmount(currentAmount.add(amount));
					break;
				case CREDIT:
					userAmount.setAmount(creditOperation(currentAmount, amount));
					break;
			}
			transaction.setAmount(amount);
			transaction.setEffectiveDate(LocalDateTime.now());
			transaction.setId(userAmount.getTransactionsHistory().size());
			transaction.setType(type);
			userAmount.getTransactionsHistory().add(transaction);
		}
		finally {
			userAmount.getLock().writeLock().unlock();
			template.convertAndSend("/topic/txTopic", toTransactionBodyMock(transaction));
		}
		
		return true;
	}
	
	@Override
	public TransactionBody findById(final Integer id) {
		final Optional<Transaction> tx = this.userAmount.getTransactionsHistory().stream().filter(u -> u.getId().equals(id)).findAny();
		if (tx.isPresent()){
			return toTransactionBodyMock(tx.get());
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	private BigDecimal creditOperation(final BigDecimal currentAmount, final BigDecimal amount) throws IllegalArgumentException{
		if (currentAmount.compareTo(amount) >= 0) {
			return currentAmount.subtract(amount);
		}
		else {
			throw new NotEnoughMoneyException();
		}
	}
}
