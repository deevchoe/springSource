package pack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pack.dto.MemberDto;
import pack.repository.MemberDao;

//@Controller
//@ResponseBody	// 내가 응답을 하는데 JSON 타입으로 응답할거야   JSON 타입을 리턴
// 위 두줄은 아래 한 줄로 표현할 수 있다.
@RestController		// 비동기처리(Ajax)에서 사용. 객체 데이터를 JSON 형태로 변환해 반환하는 역할.     @Controller + @ResponseBody      HTTP 응답 데이터(body)에 자바 객체가 매핑되어 전달된다. 
public class MemberController {
	@Autowired
	private MemberDao dao;
	
	/*
	 * 일반적인 GET, POST 방식을 스프링이 받아서 처리하는거
//	@GetMapping("/members")
//	public String list(Model model) {
//		List<MemberDto> list = dao.getList();	// 자료를 읽어다가 list에 담아가지고
//		model.addAttribute("list", list);	// 읽기 위해 model에 담아가지고
//		return "list";		// 타임리프로 출력
//	}

	@GetMapping("/members")
	public MemberDto list(Model model) {
		MemberDto dto = new MemberDto();
		dto.setNum(1);
		dto.setName("고길동");
		dto.setAddr("강남구 역삼동");
		
		return dto;
	}

	@GetMapping("/insertform")
	public String insertform() {
		return "insertform";
	}

	@PostMapping("/insert")
	public String insert(@RequestParam("name")String name, @RequestParam("addr")String addr) {
		MemberDto dto = new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		dao.insert(dto);
		
		return "redirect:/members";		// 추가 후 목록 보기
	}
	*/
	
	// ---REST 요청 처리----------------------------------------------------------
	@GetMapping("/members")		// getmapping으로 members 하면 전체 자료 읽기
	public List<MemberDto> getList(){	
		// DB 자료를 읽어
		// html 파일로 반환 X!!
		// json 형태로 변환해 클라이언트(Javascript Ajax 요청)에 반환 
		System.out.println("GET 요청 했네!!!");
		return dao.getList();	// 
	}
	
	@PostMapping("members")		// postmapping으로 members를 하면 추가.
	public Map<String, Object> insert(@RequestBody MemberDto dto) {
		// @RequestBody : (클라이언트가 http body에 담아서 보낸) 요청 본문에 담긴 값(JSON)을 자바 객체로 변환. json -> 자바 객체. 자바 객체로 바꿔줘야 insert에 참여할 수 있겠지!!
		dao.insert(dto);
		
		Map<String, Object> map = new HashMap<String, Object>(); // 성공했어~ 실패했어~를 담기 위한
		map.put("isSuccess", true);	// map을 썼기 때문에 json 형태로 리턴돼.
		return map;
	}
	
	@GetMapping("/members/{num}")		// http://localhost:80/members/3
	public MemberDto getData(@PathVariable("num")int num){	
		return dao.getData(num);
	}
	
	@PutMapping("/members/{num}")		// 수정 데이터는 body 타고 들어와.
	public Map<String, Object> update(@PathVariable("num")int num, @RequestBody MemberDto dto) {
		dto.setNum(num);
		dao.update(dto);
		
		return Map.of("isSuccess", true);
	}
	
	@DeleteMapping("/members/{num}")
	public Map<String, Object> delete(@PathVariable("num")int num){
		dao.delete(num);
		return Map.of("isSuccess", true);
	}
}
