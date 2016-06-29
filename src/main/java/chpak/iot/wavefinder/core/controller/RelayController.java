package chpak.iot.wavefinder.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class RelayController {

	@RequestMapping
	public @ResponseBody String callMqtt() {
		String returnTxt = "Hello Spring boot World";
		return returnTxt;
	}
	
}
