package pack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataDao;
import pack.model.JikwonEntity;

@Controller
public class ListController {
	
	@Autowired
	private DataDao dataDao;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("jpa")
	public String list(Model model) {
		ArrayList<JikwonEntity> jlist = (ArrayList<JikwonEntity>)dataDao.getDataAll();
		
		model.addAttribute("datas", jlist);
		return "list";
	}

	@GetMapping("search")
	public String searchList(FormBean bean, Model model) {
		ArrayList<JikwonEntity> jlist = (ArrayList<JikwonEntity>)dataDao.getDataSearch(bean.getSearchValue());	// search값만 갖고갈거야
		model.addAttribute("datas", jlist);
		return "list";
	}
}
