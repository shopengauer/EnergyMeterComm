package com.vspavlov.eventlisteners;

import com.vspavlov.fxmlcontroller.FXMLController;
import com.vspavlov.serial.SerialComPortContainer;
import javafx.application.Platform;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Vasiliy on 10.06.2015.
 */
@Component
@Scope(value = "prototype")
public class PortRxListener implements SerialPortEventListener {


    private Logger logger = LoggerFactory.getLogger(PortRxListener.class);

    @Autowired
    @Qualifier(value = "fxml")
    private FXMLController controller;


    private SerialComPortContainer serialComPortContainer;

    @Autowired
    public PortRxListener(SerialComPortContainer serialComPortContainer) {
        this.serialComPortContainer = serialComPortContainer;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

         logger.info("Event type: {}",serialPortEvent.getEventType());
         if(serialComPortContainer == null){
             logger.info("Serial container is  null");
         }

         SerialPort serialPort = serialComPortContainer.getSerialPortByComNumber(serialPortEvent.getPortName());
//
         logger.info("SerialComPortContainer size {}",serialComPortContainer.getSerialPorts().size());
       //  logger.info("RxEvent {} on port {}",serialPortEvent.getEventValue(),serialPort.getPortName());


//        && serialPortEvent.getEventValue() == 8)
        if (serialPortEvent.isRXCHAR()) {
            try {
               // byte[] buffer = serialPort.readHexString(11);
                int countBytesInBuffer = serialPort.getInputBufferBytesCount();
                logger.info("Bytes in RX buffer {}",countBytesInBuffer);
                String buffer = serialPort.readHexString(countBytesInBuffer," 0X");
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        controller.getRcvTextArea().appendText(buffer + "\n");
                    }
                });
              //  controller.getRcvTextArea().appendText(buffer);
              //  logger.info("RX message: {}",buffer.length );
                // serialPort.closePort();
            } catch (SerialPortException ex) {
                System.out.println(ex.getExceptionType());
            }
        }
     }
}