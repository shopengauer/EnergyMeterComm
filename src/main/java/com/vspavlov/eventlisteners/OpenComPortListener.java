package com.vspavlov.eventlisteners;

import com.vspavlov.fxmlcontroller.FXMLController;
import com.vspavlov.serial.SerialComPortContainer;
import javafx.scene.paint.Color;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Vasiliy on 08.06.2015.
 */
@Component
public class OpenComPortListener implements ApplicationListener<OpenComPortEvent>, SerialPortEventListener {

    private Logger logger = LoggerFactory.getLogger(OpenComPortListener.class);

    @Autowired
    @Qualifier(value = "fxml")
    private FXMLController controller;

    @Autowired
    private SerialComPortContainer serialComPortContainer;


    @Override
    public void onApplicationEvent(OpenComPortEvent openComPortEvent) {

       SerialPort serialPort = new SerialPort(openComPortEvent.getComPortName());

       try{
           serialPort.openPort();
           serialPort.setParams(openComPortEvent.getBaudrate(), openComPortEvent.getDatabits(), openComPortEvent.getStopbits(), openComPortEvent.getParity());
            logger.info("Port {} is opened: {}", serialPort.getPortName(), serialPort.isOpened());
            serialPort.addEventListener(new PortRxListener(), SerialPort.MASK_RXCHAR);
            serialComPortContainer.addSerialPort(serialPort);
       // serialPort.writeBytes("Test string".getBytes());

        //serialPort.closePort();
       }catch (SerialPortException e){
           logger.info(e.getExceptionType());
           serialPort = null;
           controller.getCircle().setFill(new Color(0.1, 0.2, 0.3, 0.4));
       }



    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }
}
