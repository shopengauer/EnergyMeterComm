package com.vspavlov.fxmlcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import jssc.SerialPort;
import jssc.SerialPortList;

import java.net.URL;
import java.util.*;

/**
 * Created by Vasiliy on 08.06.2015.
 */
public class FXMLController implements Initializable{

    @FXML
    private ComboBox<String> baudRate;

    @FXML
    private Button refresh;

    @FXML
    private AnchorPane view;

    @FXML
    private ComboBox<String> comset;


    public AnchorPane getView() {
        return view;
    }

    public void setView(AnchorPane view) {
        this.view = view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         List<String> baudRateList = new ArrayList<>();

         baudRateList.add(String.valueOf(SerialPort.BAUDRATE_1200));
          baudRateList.add(String.valueOf(SerialPort.BAUDRATE_4800));
        baudRateList.add(String.valueOf(SerialPort.BAUDRATE_9600));
        baudRateList.add(String.valueOf(SerialPort.BAUDRATE_19200));

           ObservableList<String> baudRateObslist = FXCollections.observableArrayList(baudRateList);
        System.out.println(baudRateObslist);
       baudRate.setItems(baudRateObslist);


        String[] comArray = SerialPortList.getPortNames();
        ObservableList<String> olist = FXCollections.observableArrayList(Arrays.asList(comArray));
        comset.setItems(olist);
    }

    @FXML
    void handleRefresh(ActionEvent event) {

        String[] comArray = SerialPortList.getPortNames();
        ObservableList<String> olist = FXCollections.observableArrayList(Arrays.asList(comArray));
        comset.setItems(olist);

    }


}
