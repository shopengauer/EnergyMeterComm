package com.vspavlov.fxmlcontroller;
import com.vspavlov.eventlisteners.OpenComPortEvent;
import com.vspavlov.serial.Parity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jssc.SerialPort;
import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

/**
 * Created by Vasiliy on 08.06.2015.
 */
//@Component
public class FXMLController implements Initializable,ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;
    private Logger logger = LoggerFactory.getLogger(FXMLController.class);


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        eventPublisher = applicationEventPublisher;
    }

    @FXML
    private ComboBox<?> stopbitsCombo;

    @FXML
    private Button openPort;

    @FXML
    private ComboBox<String> comPortCombo;

    @FXML
    private ComboBox<String> baudRateCombo;

    @FXML
    private ComboBox<String> parityCombo;

    @FXML
    private ComboBox<String> databitsCombo;


    @FXML
    private Button refresh;

    @FXML
    private AnchorPane view;




    public AnchorPane getView() {
        return view;
    }

    public void setView(AnchorPane view) {
        this.view = view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] comArray = SerialPortList.getPortNames();


        this.comPortCombo.getItems().addAll(comArray);
        this.baudRateCombo.getItems().addAll(String.valueOf(SerialPort.BAUDRATE_1200),
                String.valueOf(SerialPort.BAUDRATE_4800),
                String.valueOf(SerialPort.BAUDRATE_9600),
                String.valueOf(SerialPort.BAUDRATE_19200));

        this.databitsCombo.getItems().addAll(String.valueOf(SerialPort.DATABITS_7),
                String.valueOf(SerialPort.DATABITS_8));



        this.parityCombo.getItems().setAll(Parity.EVEN.toString(),
                                           Parity.NONE.toString(),
                                           Parity.ODD.toString());// addAll();





        for (String s : comArray) {
             logger.info("{} port found", s);
          }

        Iterator<String> iter =  baudRateCombo.getItems().iterator();

        while(iter.hasNext()){
            logger.info("Baud rate {}",iter.next());
        }

    }

    @FXML
    void handleRefresh(ActionEvent event) {

//        String[] comArray = SerialPortList.getPortNames();
//        this.comPortCombo.setItems(comArray);

        String[] comArray = SerialPortList.getPortNames();
        ObservableList<String> olist = FXCollections.observableArrayList(Arrays.asList(comArray));
        comPortCombo.setItems(olist);
        for (String s : comArray) {
            logger.info("{} port found", s);
        }


    }


    @FXML
    void handleOpenPort(ActionEvent event) {

        String selectedComPortName  =  comPortCombo.getValue();
        logger.info("Selected com port {}", selectedComPortName);

        //SerialPort serialPort = new SerialPort(selectedComPort);

        OpenComPortEvent apEvent = new OpenComPortEvent(this,selectedComPortName);
        eventPublisher.publishEvent(apEvent);

    }


    @FXML
    private Circle circle;

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
