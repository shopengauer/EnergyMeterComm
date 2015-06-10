package com.vspavlov.eventlisteners;

import com.vspavlov.fxmlcontroller.FXMLController;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Vasiliy on 10.06.2015.
 */
public class PortRxListener implements SerialPortEventListener{


    private Logger logger = LoggerFactory.getLogger(OpenComPortListener.class);

    @Autowired
    @Qualifier(value = "fxml")
    private FXMLController controller;


    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }
}
