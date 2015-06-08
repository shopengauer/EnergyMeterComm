package com.vspavlov.config.fxml;

import com.vspavlov.fxmlcontroller.FXMLController;
import javafx.fxml.FXMLLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Vasiliy on 08.06.2015.
 */
@Configuration
@ComponentScan
public class SpringConfig {



    @Bean
    @Qualifier(value = "fxml")
    public FXMLController fxmlController() throws IOException
    {
        System.out.println("Load controller");
        return (FXMLController) loadController("/fxml/FXMLSchema.fxml");
    }

    protected Object loadController(String url) throws IOException
    {
        try
                (InputStream fxmlStream = getClass().getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getController();
        }
    }
}