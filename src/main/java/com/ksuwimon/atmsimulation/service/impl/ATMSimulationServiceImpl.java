package com.ksuwimon.atmsimulation.service.impl;

import org.springframework.stereotype.Service;

import com.ksuwimon.atmsimulation.model.BankNotesAmountBean;
import com.ksuwimon.atmsimulation.service.ATMSimulationService;

@Service("atmSimulationService")
public class ATMSimulationServiceImpl implements ATMSimulationService {

	private BankNotesAmountBean remainingBankNoteBean = null;
	
	@Override
	public BankNotesAmountBean initializeATM(int defaultNumberOfBankNote) {
		retriveRemainingBankNote(true, setInitializeBankNotesAmountBean(defaultNumberOfBankNote));
		return remainingBankNoteBean;
	}

	@Override
	public BankNotesAmountBean initializeATM(BankNotesAmountBean bankNoteAmountBean) {
		retriveRemainingBankNote(true, bankNoteAmountBean);
		return remainingBankNoteBean;
	}

	@Override
	public BankNotesAmountBean dispenseMoney(int requestAmount) {
		BankNotesAmountBean requestBankNotesBean = new BankNotesAmountBean();
		int numberOfBankNote = 0;
		// ฿1000
		numberOfBankNote = requestAmount / 1000;
		if (requestAmount > 0 && remainingBankNoteBean.getOneThousandBathNote() > 0 && 
				numberOfBankNote > 0) {
			if (remainingBankNoteBean.getOneThousandBathNote() >= numberOfBankNote) {
				requestBankNotesBean.setOneThousandBathNote(numberOfBankNote);
				requestAmount %= 1000;
			} else {
				requestBankNotesBean.setOneThousandBathNote(remainingBankNoteBean.getOneThousandBathNote());
				requestAmount %= 1000;
				requestAmount += (1000 * (numberOfBankNote - remainingBankNoteBean.getOneThousandBathNote()));
			}
		}
		// ฿500
		numberOfBankNote = requestAmount / 500;
		if (requestAmount > 0 && remainingBankNoteBean.getFiveHundredBathNote() > 0 && 
				numberOfBankNote > 0) {
			if (remainingBankNoteBean.getFiveHundredBathNote() >= numberOfBankNote) {
				requestBankNotesBean.setFiveHundredBathNote(numberOfBankNote);
				requestAmount %= 500;
			} else {
				requestBankNotesBean.setFiveHundredBathNote(remainingBankNoteBean.getFiveHundredBathNote());
				requestAmount %= 500;
				requestAmount += (500 * (numberOfBankNote - remainingBankNoteBean.getFiveHundredBathNote()));
			}
		}
		// ฿100
		numberOfBankNote = requestAmount / 100;
		if (requestAmount > 0 && remainingBankNoteBean.getOneHundredBathNote() > 0 && 
				numberOfBankNote > 0) {
			if (remainingBankNoteBean.getOneHundredBathNote() >= numberOfBankNote) {
				requestBankNotesBean.setOneHundredBathNote(numberOfBankNote);
				requestAmount %= 100;
			} else {
				requestBankNotesBean.setOneHundredBathNote(remainingBankNoteBean.getOneHundredBathNote());
				requestAmount %= 100;
				requestAmount += (100 * (numberOfBankNote - remainingBankNoteBean.getOneHundredBathNote()));
			}
		} 
		// ฿50
		numberOfBankNote = requestAmount / 50;
		if (requestAmount > 0 && remainingBankNoteBean.getFiftyBathNote() > 0 && 
				numberOfBankNote > 0) {
			if (remainingBankNoteBean.getFiftyBathNote() >= numberOfBankNote) {
				requestBankNotesBean.setFiftyBathNote(numberOfBankNote);
				requestAmount %= 50;
			} else {
				requestBankNotesBean.setFiftyBathNote(remainingBankNoteBean.getFiftyBathNote());
				requestAmount %= 50;
				requestAmount += (50 * (numberOfBankNote - remainingBankNoteBean.getFiftyBathNote()));
			}
		}
		// ฿20
		numberOfBankNote = requestAmount / 20;
		if (requestAmount > 0 && remainingBankNoteBean.getTwentyBathNote() > 0 && 
				numberOfBankNote > 0) {
			if (remainingBankNoteBean.getTwentyBathNote() >= numberOfBankNote) {
				requestBankNotesBean.setTwentyBathNote(numberOfBankNote);
				requestAmount %= 20;
			} else {
				requestBankNotesBean.setTwentyBathNote(remainingBankNoteBean.getTwentyBathNote());
				requestAmount %= 20;
				requestAmount += (20 * (numberOfBankNote - remainingBankNoteBean.getTwentyBathNote()));
			}
		}
		
		if (requestAmount == 0) {
			retriveRemainingBankNote(false, requestBankNotesBean);
			return requestBankNotesBean;
		} else {
			return null;
		}
	}

	@Override
	public void resetBankNotes() {
		remainingBankNoteBean = setInitializeBankNotesAmountBean(0);
	}
	
	private BankNotesAmountBean setInitializeBankNotesAmountBean(int defaultNumberOfBankNote) {
		BankNotesAmountBean initializeBean = new BankNotesAmountBean();
		initializeBean.setOneThousandBathNote(defaultNumberOfBankNote);
		initializeBean.setFiveHundredBathNote(defaultNumberOfBankNote);
		initializeBean.setOneHundredBathNote(defaultNumberOfBankNote);
		initializeBean.setFiftyBathNote(defaultNumberOfBankNote);
		initializeBean.setTwentyBathNote(defaultNumberOfBankNote);
		return initializeBean;
	}

//	private BankNotesAmountBean retriveRemainingBankNote(boolean isInitializeBean, BankNotesAmountBean bankNotesBean) {
//		BankNotesAmountBean newBankNotesBean = new BankNotesAmountBean();
//		if (isInitializeBean) {
//			if (null == remainingBankNoteBean) {
//				newBankNotesBean = bankNotesBean;
//			} else {
//				newBankNotesBean.setOneThousandBathNote(remainingBankNoteBean.getOneThousandBathNote() + bankNotesBean.getOneThousandBathNote());
//				newBankNotesBean.setFiveHundredBathNote(remainingBankNoteBean.getFiveHundredBathNote() + bankNotesBean.getFiveHundredBathNote());
//				newBankNotesBean.setOneHundredBathNote(remainingBankNoteBean.getOneHundredBathNote() + bankNotesBean.getOneHundredBathNote());
//				newBankNotesBean.setFiftyBathNote(remainingBankNoteBean.getFiftyBathNote() + bankNotesBean.getFiftyBathNote());
//				newBankNotesBean.setTwentyBathNote(remainingBankNoteBean.getTwentyBathNote() + bankNotesBean.getTwentyBathNote());
//			}
//		} else {
//			if (null != bankNotesBean) {
//				newBankNotesBean.setOneThousandBathNote(remainingBankNoteBean.getOneThousandBathNote() - bankNotesBean.getOneThousandBathNote());
//				newBankNotesBean.setFiveHundredBathNote(remainingBankNoteBean.getFiveHundredBathNote() - bankNotesBean.getFiveHundredBathNote());
//				newBankNotesBean.setOneHundredBathNote(remainingBankNoteBean.getOneHundredBathNote() - bankNotesBean.getOneHundredBathNote());
//				newBankNotesBean.setFiftyBathNote(remainingBankNoteBean.getFiftyBathNote() - bankNotesBean.getFiftyBathNote());
//				newBankNotesBean.setTwentyBathNote(remainingBankNoteBean.getTwentyBathNote() - bankNotesBean.getTwentyBathNote());
//			} else {
//				newBankNotesBean = remainingBankNoteBean;
//			}
//		}
//		return newBankNotesBean;
//	}
	
	private void retriveRemainingBankNote(boolean isInitializeBean, BankNotesAmountBean bankNotesBean) {
		if (isInitializeBean) {
			if (null == remainingBankNoteBean) {
				remainingBankNoteBean = bankNotesBean;
			} else {
				remainingBankNoteBean.setOneThousandBathNote(remainingBankNoteBean.getOneThousandBathNote() + bankNotesBean.getOneThousandBathNote());
				remainingBankNoteBean.setFiveHundredBathNote(remainingBankNoteBean.getFiveHundredBathNote() + bankNotesBean.getFiveHundredBathNote());
				remainingBankNoteBean.setOneHundredBathNote(remainingBankNoteBean.getOneHundredBathNote() + bankNotesBean.getOneHundredBathNote());
				remainingBankNoteBean.setFiftyBathNote(remainingBankNoteBean.getFiftyBathNote() + bankNotesBean.getFiftyBathNote());
				remainingBankNoteBean.setTwentyBathNote(remainingBankNoteBean.getTwentyBathNote() + bankNotesBean.getTwentyBathNote());
			}
		} else {
			if (null != bankNotesBean) {
				remainingBankNoteBean.setOneThousandBathNote(remainingBankNoteBean.getOneThousandBathNote() - bankNotesBean.getOneThousandBathNote());
				remainingBankNoteBean.setFiveHundredBathNote(remainingBankNoteBean.getFiveHundredBathNote() - bankNotesBean.getFiveHundredBathNote());
				remainingBankNoteBean.setOneHundredBathNote(remainingBankNoteBean.getOneHundredBathNote() - bankNotesBean.getOneHundredBathNote());
				remainingBankNoteBean.setFiftyBathNote(remainingBankNoteBean.getFiftyBathNote() - bankNotesBean.getFiftyBathNote());
				remainingBankNoteBean.setTwentyBathNote(remainingBankNoteBean.getTwentyBathNote() - bankNotesBean.getTwentyBathNote());
			} 
		}
//		return remainingBankNoteBean;
	}

	@Override
	public BankNotesAmountBean getRemainingBankNotes() {
		return remainingBankNoteBean;
	}
}
