package chpak.iot.wavefinder.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import chpak.iot.wavefinder.core.constants.MqttClientConstants;
import chpak.iot.wavefinder.core.service.MqttClientService;
import chpak.iot.wavefinder.core.vo.MqttClientVO;

@Controller
@RequestMapping("/")
public class RelayController {

	@Autowired
	MqttClientService mqttClientService;
	
	@RequestMapping
	public @ResponseBody String welcome() {
		String returnTxt = "Hello Spring boot World";
		return returnTxt;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody String defaultController(HttpServletRequest request, HttpServletResponse response) {
		return "Welcome Chpak' World";
	}
	
	@RequestMapping("tag")
	public @ResponseBody String requestFromTwitt(HttpServletRequest request, HttpServletResponse response) {
		MqttClientVO vo = new MqttClientVO();
		
		String clientId = request.getParameter(MqttClientConstants.CLIENT_ID_KEY);
		String topic = request.getParameter(MqttClientConstants.TOPIC_KEY);
		String message = request.getParameter(MqttClientConstants.MESSAGE_KEY);
		String qosStr = request.getParameter(MqttClientConstants.QOS_KEY);
		vo.setClientId(StringUtils.isEmpty(clientId)?"":clientId);
		vo.setTopic(StringUtils.isEmpty(topic)?"":topic);
		vo.setMessage(StringUtils.isEmpty(message)?"":message);
		vo.setQos(qosStr==null?0:Integer.parseInt(qosStr));
		mqttClientService.callMosquitto(vo);
		return "Publish Completed";
	}
	
}
