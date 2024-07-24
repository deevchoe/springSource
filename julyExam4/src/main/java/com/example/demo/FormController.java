package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
	@GetMapping("kkk")
	public String aaa() {
		return "result";
	}
	
	@GetMapping("sub")
	public String submit(@RequestParam(value="name") String name, @RequestParam(value = "age") int age, Model model) {
		System.out.println("ㅇㅇ");
		DataDto dataDto = new DataDto();
		dataDto.setName(name);
		
		switch (age/10) {
		case 1: {
			dataDto.setRange("10대");
			break;
		}
		case 2: {
			dataDto.setRange("20대");
			break;
		}
		case 3: {
			dataDto.setRange("30대");
			break;
		}
		case 4: {
			dataDto.setRange("40대");
			break;
		}
		case 5: {
			dataDto.setRange("50대");
			break;
		}
		default:
			dataDto.setRange("60대 이상");
		}
		model.addAttribute("data", dataDto);
		System.out.println(dataDto.getName() + dataDto.getRange());
		return "result.html";
	}
}
