package chpak.iot.wavefinder.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
//		ModelAndView mav = new ModelAndView();
		
		vo.setClientId(request.getParameter(MqttClientConstants.CLIENT_ID_KEY));
		vo.setTopic(request.getParameter(MqttClientConstants.TOPIC_KEY));
		vo.setMessage(request.getParameter(MqttClientConstants.MESSAGE_KEY));
		vo.setQos(Integer.parseInt(request.getParameter(MqttClientConstants.QOS_KEY)));
		mqttClientService.callMosquitto(vo);
		return "Publish Completed";
	}
	
}
