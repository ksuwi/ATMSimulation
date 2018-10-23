package com.ksuwimon.atmsimulation.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ksuwimon.atmsimulation.model.BankNotesAmountBean;
import com.ksuwimon.atmsimulation.service.ATMSimulationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispenseMoneyControllerTest {

	@Autowired
	private ATMSimulationService atmSimulationService;
	
	@Test
	public void dispenseMoney_availableBankNotes() {
		
		// Reset number of Bank Note to 0
		atmSimulationService.resetBankNotes();
		
		// Initial 50 Bank Notes for each type
		BankNotesAmountBean initialBankNote = atmSimulationService.initializeATM(50);

		// Request Money = 2300
		BankNotesAmountBean actualResult = atmSimulationService.dispenseMoney(2300);
		
		BankNotesAmountBean expectedResult = new BankNotesAmountBean();
		expectedResult.setOneThousandBathNote(2);
		expectedResult.setFiveHundredBathNote(0);
		expectedResult.setOneHundredBathNote(3);
		expectedResult.setFiftyBathNote(0);
		expectedResult.setTwentyBathNote(0);
		
		testResult(expectedResult, actualResult);
			
		// Remaining Bank Note in ATM after dispensing money
		actualResult = atmSimulationService.getRemainingBankNotes();
		
		expectedResult.setOneThousandBathNote(48);
		expectedResult.setFiveHundredBathNote(50);
		expectedResult.setOneHundredBathNote(47);
		expectedResult.setFiftyBathNote(50);
		expectedResult.setTwentyBathNote(50);
		
		testResult(expectedResult, actualResult);
	}

	@Test
	public void dispenseMoney_availableBankNotes_combineBankNotes() {
		
		// Reset number of Bank Note to 0
		atmSimulationService.resetBankNotes();
		
		// Initial 10 Bank Notes for each type
		BankNotesAmountBean initialBankNote = atmSimulationService.initializeATM(10);

		// Request Money = 16000
		BankNotesAmountBean actualResult = atmSimulationService.dispenseMoney(16000);
		
		BankNotesAmountBean expectedResult = new BankNotesAmountBean();
		expectedResult.setOneThousandBathNote(10);
		expectedResult.setFiveHundredBathNote(10);
		expectedResult.setOneHundredBathNote(10);
		expectedResult.setFiftyBathNote(0);
		expectedResult.setTwentyBathNote(0);
		
		testResult(expectedResult, actualResult);
		
		// Remaining Bank Note in ATM after dispensing money
		actualResult = atmSimulationService.getRemainingBankNotes();
		
		expectedResult.setOneThousandBathNote(0);
		expectedResult.setFiveHundredBathNote(0);
		expectedResult.setOneHundredBathNote(00);
		expectedResult.setFiftyBathNote(10);
		expectedResult.setTwentyBathNote(10);
		
		testResult(expectedResult, actualResult);
	}
	
	@Test
	public void dispenseMoney_not_availableBankNotes() {
		
		// Reset number of Bank Note to 0
		atmSimulationService.resetBankNotes();
		
		// Initial 10 Bank Notes for each type
		BankNotesAmountBean initialBankNote = atmSimulationService.initializeATM(10);

		// Request Money = 730
		BankNotesAmountBean actualResult = atmSimulationService.dispenseMoney(730);
	
		// Bean will be null in case of invalid combination
		Assert.assertNull(actualResult);

		// Remaining Bank Note in ATM after dispensing money
		actualResult = atmSimulationService.getRemainingBankNotes();
		
		BankNotesAmountBean expectedResult = new BankNotesAmountBean();
		expectedResult.setOneThousandBathNote(10);
		expectedResult.setFiveHundredBathNote(10);
		expectedResult.setOneHundredBathNote(10);
		expectedResult.setFiftyBathNote(10);
		expectedResult.setTwentyBathNote(10);
		
		testResult(expectedResult, actualResult);
	}
	
	@Test
	public void dispenseMoney_requestMoreThanAvailableBankNote() {
		
		// Reset number of Bank Note to 0
		atmSimulationService.resetBankNotes();
		
		// Initial 10 Bank Notes for each type
		BankNotesAmountBean initialBankNote = atmSimulationService.initializeATM(10);

		// Request Money = 20000
		BankNotesAmountBean actualResult = atmSimulationService.dispenseMoney(20000);
	
		// Bean will be null in case of invalid combination
		Assert.assertNull(actualResult);
		
		// Remaining Bank Note in ATM after dispensing money
		actualResult = atmSimulationService.getRemainingBankNotes();
		
		BankNotesAmountBean expectedResult = new BankNotesAmountBean();
		expectedResult.setOneThousandBathNote(10);
		expectedResult.setFiveHundredBathNote(10);
		expectedResult.setOneHundredBathNote(10);
		expectedResult.setFiftyBathNote(10);
		expectedResult.setTwentyBathNote(10);
		
		testResult(expectedResult, actualResult);
	}
	
	private void testResult(BankNotesAmountBean expectedResult, BankNotesAmountBean actualResult) {
		Assert.assertEquals(expectedResult.getOneThousandBathNote(), actualResult.getOneThousandBathNote());
		Assert.assertEquals(expectedResult.getFiveHundredBathNote(), actualResult.getFiveHundredBathNote());
		Assert.assertEquals(expectedResult.getOneHundredBathNote(), actualResult.getOneHundredBathNote());
		Assert.assertEquals(expectedResult.getFiftyBathNote(), actualResult.getFiftyBathNote());
		Assert.assertEquals(expectedResult.getTwentyBathNote(), actualResult.getTwentyBathNote());
	}


}
