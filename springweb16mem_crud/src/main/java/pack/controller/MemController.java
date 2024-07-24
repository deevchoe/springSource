package pack.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.DataProcess;
import pack.model.Mem;

@Controller
public class MemController {
	@Autowired
	private DataProcess dataProcess;
	
	@GetMapping("/")
	public String main() {
		return "chulbal";
	}

	@GetMapping("list")
	public String list(Model model) {	// 클라이언트를 통하지않기 때문에 얘를 만날 수 없기 때문에 redirect를 해줘야 한다.
		ArrayList<Mem> list = (ArrayList<Mem>)dataProcess.getDataAll();
		model.addAttribute("datas", list);
		return "list";
	}
	
	// 추가
	@GetMapping("insert")
	public String insert() {
		return "insert";
	}
	
	@PostMapping("insert")
	public String insertProcess(MemBean bean, Model model) {
		
		String msg = dataProcess.insert(bean);
		
		if(msg.equals("success"))
			return "redirect:/list";
		else {
			model.addAttribute("msg", msg);	// 실패했을 때는 메세지를 담아 error 페이지로 이동
			return "error";			
		}
	}
	
	// 수정
	@GetMapping("update")
	public String update(@RequestParam("num") String num, Model model) {
		//System.out.println("num : " + num);
		Mem mem = dataProcess.getData(num);
		
		model.addAttribute("data", mem);
		return "update";
	}
	
	@PostMapping("update")
	public String updateProcess(MemBean bean, Model model) {
		
		String msg = dataProcess.update(bean);
		
		if(msg.equals("success"))
			return "redirect:/list";
		else {
			model.addAttribute("msg", msg);	// 실패했을 때는 메세지를 담아 error 페이지로 이동
			return "error";			
		}
	}

	@GetMapping("delete")
	public String deleteProcess(@RequestParam("num") int num, Model model) {
		String msg = dataProcess.delete(num);
		
		if(msg.equals("success"))
			return "redirect:/list";
		else {
			model.addAttribute("msg", msg);	// 실패했을 때는 메세지를 담아 error 페이지로 이동
			return "error";			
		}
	}
	
	@GetMapping("error")
	public String error() {
		return "error";
	}
}
