package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.MemberDao;
import pack.model.MemberDto;

@Controller
public class DeleteController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("delete")
	public String form(@RequestParam("id") String id) {
		memberDao.delData(id); // id를 들고가ㅏ
		return "redirect:/list";
	}
}
