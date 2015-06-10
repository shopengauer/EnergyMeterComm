package com.vspavlov.eventlisteners;

import com.vspavlov.fxmlcontroller.FXMLController;
import com.vspavlov.serial.Parity;
import javafx.scene.paint.Color;
import jssc.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import static jssc.SerialPort.*;
/**
 * Created by Vasiliy on 08.06.2015.
 */
//@Component
public class OpenComPortEvent extends ApplicationEvent {

    private String comPortName;
    private int baudrate;
    private int databits;
    private int stopbits;
    private int parity;


    public OpenComPortEvent(Object source) {
        super(source);
    }

    public OpenComPortEvent(Object source, String comPortName) {
        this(source, comPortName, BAUDRATE_9600, DATABITS_8,STOPBITS_1,PARITY_NONE);
    }

    public OpenComPortEvent(Object source,String comPortName, int baudrate) {
        this(source, comPortName, baudrate,DATABITS_8,STOPBITS_1,PARITY_NONE);
    }

    public OpenComPortEvent(Object source,String comPortName, int baudrate, int databits, int stopbits, int parity) {
        super(source);
        this.comPortName = comPortName;
        this.baudrate = baudrate;
        this.databits = databits;
        this.stopbits = stopbits;
        this.parity = parity;
    }


    @Override
    public Object getSource() {
        return super.getSource();
    }

    public String getComPortName() {
        return comPortName;
    }

    public int getBaudrate() {
        return baudrate;
    }

    public int getDatabits() {
        return databits;
    }

    public int getStopbits() {
        return stopbits;
    }

    public int getParity() {
        return parity;
    }
}
