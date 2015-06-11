package com.vspavlov;

import com.vspavlov.eventlisteners.PortRxListener;
import com.vspavlov.serial.SerialComPortContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EnergyMeterCommApplication.class)
public class EnergyMeterCommApplicationTests {

	private Logger logger = LoggerFactory.getLogger(PortRxListener.class);

	@Autowired
	private SerialComPortContainer serialComPortContainer;

	@Test
	public void contextLoads() {

	   logger.info(serialComPortContainer.getSerialPorts().toString());

	}

}
