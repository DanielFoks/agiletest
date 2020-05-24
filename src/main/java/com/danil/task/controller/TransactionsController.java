package com.danil.task.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danil.task.exception.NotEnoughMoneyException;
import com.danil.task.model.TransactionBody;
import com.danil.task.service.UserAmountService;

@RestController
public class TransactionsController {
	private final UserAmountService userAmountService;
	
	public TransactionsController(final UserAmountService userAmountService) {
		this.userAmountService = userAmountService;
	}
	
	@PostMapping("/addTransaction/all")
	public void addTransaction(final TransactionBody transactionBody){
		userAmountService.writeAmount(transactionBody);
	}
	
	@GetMapping("/transaction")
	public TransactionBody getById(@PathVariable final Integer id){
		return userAmountService.findById(id);
	}
	
	@GetMapping("/transaction")
	public List<TransactionBody> allTransactions(){
		return userAmountService.getTransactions();
	}
	
	
	@ExceptionHandler({NotEnoughMoneyException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void handleException() {
	}
}
