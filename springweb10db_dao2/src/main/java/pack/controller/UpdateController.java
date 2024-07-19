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
public class UpdateController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("update")
	public String form(@RequestParam("id") String id, Model model) {
		MemberDto dto = memberDao.getMember(id); // id를 들고가ㅏ
		model.addAttribute("member", dto);	// member라는 키에다가 dto를 담을거야
		return "upform";
	}
	
	@PostMapping("update")
	public String update(MemberBean bean) {
		memberDao.upData(bean);
		
		return "redirect:/list";
	}
}
