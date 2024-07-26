package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.dto.GogekDto;
import pack.dto.JikwonDto;
import pack.entity.Jikwon;
import pack.repository.GogekRepository;
import pack.repository.JikwonRepository;

@Controller
public class TestController {
	
	@Autowired
	private JikwonRepository jikwonRepo;

	@Autowired
	private GogekRepository gogekRepo;
	
	@GetMapping("look")
	public String jikwonlist(Model model) {
		List<JikwonDto> list = jikwonRepo.findAll()
				.stream()
				.map(JikwonDto::toDto)
				.toList();	// JPQL
				
		model.addAttribute("list", list);
		return "jlist";
	}
	
	@GetMapping("/mygogek")	
	public String mygogek(@RequestParam("jno")int jno, Model model) {
		// 사원명, 직급
		JikwonDto dto = JikwonDto.toDto(jikwonRepo.findByJno(jno));		
		model.addAttribute("data", dto);
		
		// 고객 리스트
		List<GogekDto> glist = gogekRepo.findByJikwonJno(jno)
										.stream()
										.map(GogekDto::toDto)
										.toList();
		model.addAttribute("list", glist);
		
		// 고객 수
		model.addAttribute("listsize", glist.size());
		return "gogek";
	}
}
