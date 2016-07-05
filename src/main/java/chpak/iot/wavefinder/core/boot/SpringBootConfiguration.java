package chpak.iot.wavefinder.core.boot;

import io.moquette.BrokerConstants;
import io.moquette.server.Server;
import io.moquette.server.config.IConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

@ComponentScan("chpak.iot.wavefinder.*")
@EnableAutoConfiguration
public class SpringBootConfiguration {

	public static void main(String[] args) throws IOException{
		init();
//		SpringApplication.run(SpringBootConfiguration.class, args); 
		
		final Server server = new Server();
		String nettyPort = System.getProperty("WEB_SOCKET_PORT");
		NettyConfig config = new NettyConfig();
		config.setProperty(BrokerConstants.PORT_PROPERTY_NAME, "1833");
		config.setProperty(BrokerConstants.WEB_SOCKET_PORT_PROPERTY_NAME, nettyPort);
		config.setProperty(BrokerConstants.HOST_PROPERTY_NAME, "0.0.0.0");
        server.startServer(config);
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

class NettyConfig implements IConfig {
	private Map<String, String> configMap = new HashMap<String, String>();
	@Override
	public void setProperty(String name, String value) {
		configMap.put(name, value);
	}

	@Override
	public String getProperty(String name) {
		return configMap.get(name);
	}

	@Override
	public String getProperty(String name, String defaultValue) {
		String value = getProperty(name);
		if(StringUtils.isEmpty(value)) {
			return defaultValue;
		} 
		return value;
	}
}
