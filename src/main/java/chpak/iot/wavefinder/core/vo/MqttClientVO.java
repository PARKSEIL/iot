package chpak.iot.wavefinder.core.vo;

public class MqttClientVO {

	
	private String clientId;
	private String topic;
	private String message;
	private String mqttServerType;
	private int qos;
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getQos() {
		return qos;
	}
	public void setQos(int qos) {
		this.qos = qos;
	}
	public String getMqttServerType() {
		return mqttServerType;
	}
	public void setMqttServerType(String mqttServerType) {
		this.mqttServerType = mqttServerType;
	}
	
	
}
