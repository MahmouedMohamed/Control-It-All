package com.ogive.ogive;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@EnableAutoConfiguration
public class OgiveApplication {

    @RequestMapping("/sleep")
    String sleep() {
    	try {
			Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
			return("Done");
    	} catch (IOException e) {
			return(e.toString());
		}
    }
    @Configuration
    public class WebConfig implements WebMvcConfigurer {      
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/","classpath:/image/")
            .setCachePeriod(0);
        }
    }
    @RequestMapping("/screenshot")
    String test() throws IOException {
			try {
				System.setProperty("java.awt.headless", "false");
				Robot bot = new Robot(); 

	            String path = "C://Users//mahmo//Downloads//ogive//ogive//target//classes//image//Shot.jpg"; 
	            Rectangle capture =  
	            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
	            BufferedImage Image = bot.createScreenCapture(capture); 
	            ImageIO.write(Image, "jpg", new File(path)); 
	            System.out.println("Screenshot saved");
	            return "Shot.jpg";
//	            return encodedImage;
//	            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//					double width = screenSize.getWidth();
//					double height = screenSize.getHeight();
//					System.out.print(width+" "+height);
//				bot = new Robot();
//				bot.mouseMove((int)width/2,(int)height/2);    
////				bot.keyPress(KeyEvent.);
//	    	    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//	    	    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			} catch (AWTException e) {
				e.printStackTrace();
			}
			return null;
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(OgiveApplication.class, args);
    }

}