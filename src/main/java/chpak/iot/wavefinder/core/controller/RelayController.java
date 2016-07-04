package chpak.iot.wavefinder.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import chpak.iot.wavefinder.core.service.MqttManagerService;

@Controller
@RequestMapping("/")
public class RelayController {

	@Autowired
	MqttManagerService mqttManagerService;
	
	@RequestMapping
	public @ResponseBody String welcome() {
		String returnTxt = "Hello Spring boot World";
		return returnTxt;
	}
	
	@RequestMapping("/tag/twitt/*")
	public @ResponseBody ModelAndView requestFromTwitt(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
}
