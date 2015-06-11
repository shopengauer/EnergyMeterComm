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
public class OpenComPortListener implements ApplicationListener<OpenComPortEvent>{

    private Logger logger = LoggerFactory.getLogger(OpenComPortListener.class);

    @Autowired
    @Qualifier(value = "fxml")
    private FXMLController controller;

    @Autowired
    private SerialComPortContainer serialComPortContainer;
    @Autowired
    private PortRxListener portRxListener;


    @Override
    public void onApplicationEvent(OpenComPortEvent openComPortEvent) {

       SerialPort serialPort = new SerialPort(openComPortEvent.getComPortName());

       try{
           serialPort.openPort();
          int baudRate = openComPortEvent.getBaudrate();
          int databits = openComPortEvent.getDatabits();
          int stopBits =  openComPortEvent.getStopbits();
          int parity = openComPortEvent.getParity();

           serialPort.setParams(baudRate, databits, stopBits, parity);
            logger.info("Port {} is opened: {}", serialPort.getPortName(), serialPort.isOpened());
          logger.info("Baudrate: {}, Databits {}, Stopbits: {}, Parity: {}", baudRate,databits,stopBits,parity);

           serialPort.addEventListener(portRxListener, SerialPort.MASK_RXCHAR);
            serialComPortContainer.addSerialPort(serialPort);
            logger.info("Serial port size {}",serialComPortContainer.getSerialPorts().size());
       // serialPort.writeBytes("Test string".getBytes());

        //serialPort.closePort();
       }catch (SerialPortException e){
           logger.info(e.getExceptionType());

           serialPort = null;
           controller.getCircle().setFill(new Color(0.1, 0.2, 0.3, 0.4));
       }

    }

}
