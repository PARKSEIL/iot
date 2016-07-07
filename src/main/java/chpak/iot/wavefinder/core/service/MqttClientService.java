package chpak.iot.wavefinder.core.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import chpak.iot.wavefinder.core.constants.MqttClientConstants;
import chpak.iot.wavefinder.core.vo.MqttClientVO;

@Service("mqttClientService")
public class MqttClientService {
	private Logger log = LoggerFactory.getLogger(MqttClientService.class);
	
	public void callMosquitto(MqttClientVO vo) {
		String clientId     = vo.getClientId();
		String topic        = vo.getTopic();
        String content      = vo.getMessage();
        int qos             = vo.getQos();
        String broker       = MqttClientConstants.BROKER_URL;
        
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient client = null;
        try {
            client = new MqttClient(broker, clientId, persistence);
            client.setTimeToWait(5000);
            MqttTopic topicObj = client.getTopic(topic);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            MqttDeliveryToken token = null;
            setConnOptiont(connOpts);
            log.info("[Topic : "+topic+"]");
            log.info("[Connecting to broker: "+broker+"]");
            
            client.connect(connOpts);
            
            log.info("[Connected Broker]");
            log.info("[Publishing message: "+content+"]");
            
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            message.setRetained(false);
            
            token = topicObj.publish(message);
//            client.publish(topic, message);
            log.info("[Message published, token id="+token+"]");
//            System.exit(0);
        } catch(MqttException me) {
        	log.error("[reason :"+me.getReasonCode()+"]");
        	log.error("[msg :"+me.getMessage()+"]");
        	log.error("[loc :"+me.getLocalizedMessage()+"]");
        	log.error("[cause :"+me.getCause()+"]");
        	log.error("[excep :"+me+"]");
            me.printStackTrace();
//            System.exit(-1);
        } finally {
        	try {
				disconnect(client);
			} catch (MqttException e) {
				e.printStackTrace();
			}
        	
        }
	}
	
	private void disconnect(MqttClient client) throws MqttException {
		if(client!= null) {
			client.disconnect();
		}
		log.debug("Disconnected");
	}
	private void setConnOptiont(MqttConnectOptions connOpt) {
		connOpt = new MqttConnectOptions();
		connOpt.setCleanSession(true);
		connOpt.setKeepAliveInterval(30);
//		connOpt.setUserName(M2MIO_USERNAME);
//		connOpt.setPassword(M2MIO_PASSWORD_MD5.toCharArray());
	}
	
}
