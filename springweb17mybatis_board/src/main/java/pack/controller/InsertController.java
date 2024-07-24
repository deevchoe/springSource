package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pack.model.BoardDao;

@Controller
public class InsertController {
	@Autowired
	private BoardDao dao;
	
	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert() {
		return "insform";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insertSummit(BoardBean bean) {
		boolean b = dao.insertData(bean);
		
		if(b) {
			return "redirect:/list";	// 리스트 컨트롤러를 만나 추가 후 목록 보기 가능
		}else {
			return "error";			
		}
	}
	
	
}
