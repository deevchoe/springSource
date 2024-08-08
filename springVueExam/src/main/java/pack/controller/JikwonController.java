package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pack.dto.JikwonDto;
import pack.model.DataDao;

@CrossOrigin("*")
@RestController
public class JikwonController {

	@Autowired
	private DataDao dao;
	
	@GetMapping("/jikwon/")
	public List<JikwonDto> getJikwonAll(){
		return dao.getJikwonAll();
	}
	
	@GetMapping("/jikwon/{jik}")
	public List<JikwonDto> getSelectJikwon(@PathVariable("jik") String jik){
		return dao.getSelectJikwon(jik);
	}
}
