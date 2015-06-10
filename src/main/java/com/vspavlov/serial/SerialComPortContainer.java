package com.vspavlov.serial;

import com.vspavlov.eventlisteners.OpenComPortEvent;
import jssc.SerialPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasiliy on 10.06.2015.
 */
@Component
public class SerialComPortContainer {

    private List<SerialPort> serialPorts = new ArrayList<>();

    public void addSerialPort(SerialPort serialPort) {
        serialPorts.add(serialPort);
    }

//    public void deleteSerialPort(SerialPort serialPort) {
//        serialPorts.remove(serialPort);
//    }

    public List<SerialPort> getSerialPorts() {
        return serialPorts;
    }
}
