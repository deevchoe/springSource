package pack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataDao;
import pack.model.DataDto;

@Controller
public class ListController {
	
	@Autowired
	private DataDao dataDao;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("mybatis")
	public String listProcess(@RequestParam(value="jikwon_jik") String jikwon_jik, Model model) {
		List<DataDto> list = dataDao.getDataSearch(jikwon_jik);
		
		model.addAttribute("datas", list);
		return "list";
	}

}
