package com.ksuwimon.atmsimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksuwimon.atmsimulation.model.BankNotesAmountBean;
import com.ksuwimon.atmsimulation.model.ResultJson;
import com.ksuwimon.atmsimulation.service.ATMSimulationService;

@RestController
@RequestMapping("/atm")
public class ATMSimulationController {

	@Autowired
	private ATMSimulationService atmSimulationService;
	
	@GetMapping("/initial")
	public ResultJson initializeATM() {
		int defaultNumberOfBankNote = 100;
		BankNotesAmountBean remainingBankNoteBean = atmSimulationService.initializeATM(defaultNumberOfBankNote);
		return getResultJson(1, remainingBankNoteBean, "Successful to fetch available cash");
	}
	
	@GetMapping("/initial/{defaultNumberOfBankNote}")
	public ResultJson initializeATM(@PathVariable int defaultNumberOfBankNote) {
		BankNotesAmountBean remainingBankNoteBean = atmSimulationService.initializeATM(defaultNumberOfBankNote);
		return getResultJson(1, remainingBankNoteBean, "Successful to fetch available cash");
	} 

	@PostMapping("/initial")
	public ResultJson initializeATM(@RequestBody BankNotesAmountBean initialBankNote) {
		BankNotesAmountBean remainingBankNoteBean = atmSimulationService.initializeATM(initialBankNote);
		return getResultJson(1, remainingBankNoteBean, "Successful to fetch available cash");
	}
	
	@GetMapping("/request/{requestAmount}")
	public ResultJson dispenseMoney(@PathVariable int requestAmount) {
		BankNotesAmountBean requestBankNotesBean = atmSimulationService.dispenseMoney(requestAmount);
		if (null != requestBankNotesBean) {
			return  getResultJson(1, requestBankNotesBean, "Successful to dispense money");
		} else {
			return getResultJson(0, requestBankNotesBean, "Failure to dispense money");
		}
	}
	
	@GetMapping("/currentnotes")
	public BankNotesAmountBean getRemainingBankNotes() {
		return atmSimulationService.getRemainingBankNotes();
	}
	
	@GetMapping("/reset")
	public ResultJson resetBankNotes() {
		atmSimulationService.resetBankNotes();
		return  getResultJson(1, null, "Successful to reset cash to zero ");
	}
	
	private ResultJson getResultJson(int success, Object bean, String message) {
		return new ResultJson(success, bean, message);
	}

}
