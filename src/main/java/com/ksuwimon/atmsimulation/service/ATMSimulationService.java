package com.ksuwimon.atmsimulation.service;

import com.ksuwimon.atmsimulation.model.BankNotesAmountBean;

public interface ATMSimulationService {
	public BankNotesAmountBean initializeATM(int defaultNumberOfBankNote);
	public BankNotesAmountBean initializeATM(BankNotesAmountBean bankNoteAmountBean);
	public BankNotesAmountBean dispenseMoney(int requestAmount);
	public BankNotesAmountBean getRemainingBankNotes();
	public void resetBankNotes();
}
