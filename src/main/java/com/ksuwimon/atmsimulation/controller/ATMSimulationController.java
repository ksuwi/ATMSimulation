package com.ksuwimon.atmsimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksuwimon.atmsimulation.model.BankNotesAmountBean;
import com.ksuwimon.atmsimulation.service.ATMSimulationService;

@RestController
@RequestMapping("/atm")
public class ATMSimulationController {

	@Autowired
	private ATMSimulationService atmSimulationService;
	
	private BankNotesAmountBean remainingBankNoteBean = null;

	@GetMapping("/initial")
	public BankNotesAmountBean initializeATM() {
		int defaultNumberOfBankNote = 100;
		remainingBankNoteBean = retriveRemainingBankNote(true, atmSimulationService.initializeATM(defaultNumberOfBankNote));
		return remainingBankNoteBean; 
	}
	
	@GetMapping("/initial/default/{defaultNumberOfBankNote}")
	public BankNotesAmountBean initializeATM(@PathVariable int defaultNumberOfBankNote) {
		remainingBankNoteBean = retriveRemainingBankNote(true, atmSimulationService.initializeATM(defaultNumberOfBankNote));
		return remainingBankNoteBean;
	} 

	@PostMapping("/initial/")
	public BankNotesAmountBean initializeATM(BankNotesAmountBean bankNoteAmountBean) {
		remainingBankNoteBean = retriveRemainingBankNote(true, bankNoteAmountBean);
		return remainingBankNoteBean;
	}
	
	@GetMapping("/request/{requestAmount}")
	public BankNotesAmountBean dispenseMoney(@PathVariable int requestAmount) {
		BankNotesAmountBean requestBankNotesBean = atmSimulationService.dispenseMoney(remainingBankNoteBean, requestAmount);
		remainingBankNoteBean = retriveRemainingBankNote(false, requestBankNotesBean);
		return requestBankNotesBean;
	}
	
	@GetMapping("/currentnotes")
	public BankNotesAmountBean getRemainingBankNotes() {
		return remainingBankNoteBean;
	}
	
	@GetMapping("/reset")
	public BankNotesAmountBean resetBankNotes() {
		remainingBankNoteBean = atmSimulationService.resetBankNotes();
		return remainingBankNoteBean;
	}
	
	private BankNotesAmountBean retriveRemainingBankNote(boolean isInitializeBean, BankNotesAmountBean bankNotesBean) {
		BankNotesAmountBean newBankNotesBean = new BankNotesAmountBean();
		if (isInitializeBean) {
			if (null == remainingBankNoteBean) {
				newBankNotesBean = bankNotesBean;
			} else {
				newBankNotesBean.setOneThousandBathNote(remainingBankNoteBean.getOneThousandBathNote() + bankNotesBean.getOneThousandBathNote());
				newBankNotesBean.setFiveHundredBathNote(remainingBankNoteBean.getFiveHundredBathNote() + bankNotesBean.getFiveHundredBathNote());
				newBankNotesBean.setOneHundredBathNote(remainingBankNoteBean.getOneHundredBathNote() + bankNotesBean.getOneHundredBathNote());
				newBankNotesBean.setFiftyBathNote(remainingBankNoteBean.getFiftyBathNote() + bankNotesBean.getFiftyBathNote());
				newBankNotesBean.setTwentyBathNote(remainingBankNoteBean.getTwentyBathNote() + bankNotesBean.getTwentyBathNote());
			}
		} else {
			if (null != bankNotesBean) {
				newBankNotesBean.setOneThousandBathNote(remainingBankNoteBean.getOneThousandBathNote() - bankNotesBean.getOneThousandBathNote());
				newBankNotesBean.setFiveHundredBathNote(remainingBankNoteBean.getFiveHundredBathNote() - bankNotesBean.getFiveHundredBathNote());
				newBankNotesBean.setOneHundredBathNote(remainingBankNoteBean.getOneHundredBathNote() - bankNotesBean.getOneHundredBathNote());
				newBankNotesBean.setFiftyBathNote(remainingBankNoteBean.getFiftyBathNote() - bankNotesBean.getFiftyBathNote());
				newBankNotesBean.setTwentyBathNote(remainingBankNoteBean.getTwentyBathNote() - bankNotesBean.getTwentyBathNote());
			} else {
				newBankNotesBean = remainingBankNoteBean;
			}
		}
		return newBankNotesBean;
	}

}
