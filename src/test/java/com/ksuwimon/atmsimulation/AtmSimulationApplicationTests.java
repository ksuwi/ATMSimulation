package com.ksuwimon.atmsimulation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.ksuwimon.atmsimulation.controller.DispenseMoneyControllerTest;
import com.ksuwimon.atmsimulation.controller.InitializeATMControllerTest;

@RunWith(Suite.class)
@SuiteClasses({ DispenseMoneyControllerTest.class, InitializeATMControllerTest.class })
public class AtmSimulationApplicationTests {

}
