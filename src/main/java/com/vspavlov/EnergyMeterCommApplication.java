package com.vspavlov;

import com.vspavlov.fxmlcontroller.FXMLController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
@ComponentScan(basePackages = "com.vspavlov.*")
public class EnergyMeterCommApplication extends AbstractJavaFxApplicationSupport{

//    public static void main(String[] args) {
//        SpringApplication.run(EnergyMeterCommApplication.class, args);
//        launch(args);
//    }


    @Autowired
    @Qualifier(value = "fxml")
    private FXMLController fxmlController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        SerialPort serialPort = new SerialPort("COM3");
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("Hello World!!!".getBytes()));
            System.out.println("Port closed: " + serialPort.closePort());
        }
        catch (SerialPortException ex){
            System.out.println(ex);
        }


        Scene scene = new Scene((Parent)fxmlController.getView(), 1000, 700);
        // fxmlController.getStartButton().setText("Hello wasya");
      //  scene.getStylesheets().add("/styles/fxmlschema.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Energymeter communication client");
        primaryStage.show();


}

    public static void main(String[] args) {
    launchApp(EnergyMeterCommApplication.class, args);
      }
}
