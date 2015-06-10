package com.vspavlov.aspects;

import com.vspavlov.fxmlcontroller.FXMLController;
import jssc.SerialPortList;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Created by Vasiliy on 09.06.2015.
 */
@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
    //@Pointcut("execution(** com.vspavlov.fxmlcontroller.FXMLController.initialize(..))")
//    @Pointcut("execution(void  com.vspavlov.fxmlcontroller.FXMLController.initialize(..))")
//    public void logging() {}
//
//    @Before(value = "logging()")
//    public void initializeLogging(){
//
//            String[] comArray = SerialPortList.getPortNames();
//            for (String s : comArray) {
//                logger.info("{} port found in Aspect", s);
//            }
//        }
//

}
