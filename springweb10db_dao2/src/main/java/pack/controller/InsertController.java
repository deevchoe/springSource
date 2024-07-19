package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pack.model.MemberDao;

@Controller
public class InsertController {

	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("insert")
	public String form() {
		return "insform";
	}
	
	@PostMapping("insert")
	public String submit(MemberBean bean) {
		memberDao.insData(bean);
		
		//return "list"; // 포워딩하면 추가한 내용이 안보인다. 그렇기 때문에 redirect를 써서 클라이언트를 통해야 한다.
		return "redirect:/list"; // 이렇게 해줘야 추가 후 목록보기가 가능
	}
}
