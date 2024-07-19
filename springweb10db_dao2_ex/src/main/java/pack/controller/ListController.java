package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.MemberDao;
import pack.model.MemberDto;

@Controller
public class ListController {
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("testdb")
	public String listProcess(@RequestParam(value="jikwon_jik") String jikwon_jik, Model model) {
		List<MemberDto> list = memberDao.getMemberList(jikwon_jik);
		
		model.addAttribute("datas", list);
		return "list";
	}
}
