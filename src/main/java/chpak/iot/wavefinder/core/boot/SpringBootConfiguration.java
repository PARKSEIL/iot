package chpak.iot.wavefinder.core.boot;

import io.moquette.server.Server;

import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("chpak.iot.wavefinder.*")
@EnableAutoConfiguration
public class SpringBootConfiguration {

	public static void main(String[] args) throws IOException{
		init();
//		SpringApplication.run(SpringBootConfiguration.class, args); 
		
		final Server server = new Server();
        server.startServer();
        System.out.println("Server started, version 0.9-SNAPSHOT");
        //Bind  a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.stopServer();
            }
        });
	}
	
	private static void init() {
//		System.setProperty("moquette.path",  "config/moquette.conf"); //default path
	}

}
