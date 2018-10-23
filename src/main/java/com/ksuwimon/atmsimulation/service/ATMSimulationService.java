package com.ksuwimon.atmsimulation.service;

import com.ksuwimon.atmsimulation.model.BankNotesAmountBean;

public interface ATMSimulationService {
	public BankNotesAmountBean initializeATM(int defaultNumberOfBankNote);
//	public void initializeATM(BankNotesAmountBean remainingBankNoteBean, BankNotesAmountBean bankNoteAmountBean);
	public BankNotesAmountBean dispenseMoney(BankNotesAmountBean remainingBankNoteBean, int requestAmount);
	public BankNotesAmountBean resetBankNotes();
}
