package chpak.iot.wavefinder.core.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("chpak.iot.wavefinder.*")
@EnableAutoConfiguration
public class SpringBootConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConfiguration.class, args);
	}

}
