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
public class InitializeATMControllerTest {

	@Autowired
	private ATMSimulationService atmSimulationService;
	
	@Test
	public void initializeATM_withoutRemainingCash() {
		
		// Reset number of Bank Note to 0
		atmSimulationService.resetBankNotes();

		// Initial 50 Bank Notes for each type  
		int defaultNumberOfBankNote = 50;
		BankNotesAmountBean expectedResult = new BankNotesAmountBean();
		expectedResult.setOneThousandBathNote(defaultNumberOfBankNote);
		expectedResult.setFiveHundredBathNote(defaultNumberOfBankNote);
		expectedResult.setOneHundredBathNote(defaultNumberOfBankNote);
		expectedResult.setFiftyBathNote(defaultNumberOfBankNote);
		expectedResult.setTwentyBathNote(defaultNumberOfBankNote);
		
		BankNotesAmountBean actualResult = atmSimulationService.initializeATM(defaultNumberOfBankNote);
		
		testResult(expectedResult, actualResult);
	}
	
	@Test
	public void initializeATM_withRemainingCash() {
		
		// Reset number of Bank Note to 0
		atmSimulationService.resetBankNotes();
		
		// Remaining cash for each Bank Notes = 8
		int remainingBankNote = 8;
		atmSimulationService.initializeATM(remainingBankNote);

		// Initial 50 Bank Notes for each type 
		int defaultNumberOfBankNote = 50;
		BankNotesAmountBean expectedResult = new BankNotesAmountBean();
		expectedResult.setOneThousandBathNote(remainingBankNote + defaultNumberOfBankNote);
		expectedResult.setFiveHundredBathNote(remainingBankNote + defaultNumberOfBankNote);
		expectedResult.setOneHundredBathNote(remainingBankNote + defaultNumberOfBankNote);
		expectedResult.setFiftyBathNote(remainingBankNote + defaultNumberOfBankNote);
		expectedResult.setTwentyBathNote(remainingBankNote + defaultNumberOfBankNote);
		
		BankNotesAmountBean actualResult = atmSimulationService.initializeATM(defaultNumberOfBankNote);

		testResult(expectedResult, actualResult);
	}
	
	@Test
	public void initializeATM_withRemainingCash_specificNumberOfBankNote() {
		
		// Reset number of Bank Note to 0
		atmSimulationService.resetBankNotes();
				
		// Remaining cash for each Bank Notes = 10
		int remainingBankNote = 10;
		atmSimulationService.initializeATM(remainingBankNote);
		
		// Initial number of Bank Notes for each type
		BankNotesAmountBean initialBankNote = new BankNotesAmountBean();
		initialBankNote.setOneThousandBathNote(20);
		initialBankNote.setFiveHundredBathNote(40);
		initialBankNote.setOneHundredBathNote(60);
		initialBankNote.setFiftyBathNote(80);
		initialBankNote.setTwentyBathNote(100);

		BankNotesAmountBean expectedResult = new BankNotesAmountBean();
		expectedResult.setOneThousandBathNote(remainingBankNote + 20);
		expectedResult.setFiveHundredBathNote(remainingBankNote + 40);
		expectedResult.setOneHundredBathNote(remainingBankNote + 60);
		expectedResult.setFiftyBathNote(remainingBankNote + 80);
		expectedResult.setTwentyBathNote(remainingBankNote + 100);
		
		BankNotesAmountBean actualResult = atmSimulationService.initializeATM(initialBankNote);

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
