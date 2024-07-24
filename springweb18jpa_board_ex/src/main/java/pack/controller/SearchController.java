package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.model.Jikwon;
import pack.model.JikwonDao;

@Controller
public class SearchController {
    @Autowired
    private JikwonDao dao;

    @PostMapping("/search")
    public String searchJikwon(Model model, @RequestParam("searchBuser") int searchBuser,
    										@RequestParam("searchRating") String searchRating) {
		List<Jikwon> jikwonList = dao.list(searchBuser, searchRating);
		model.addAttribute("list", jikwonList);
		
		// 연봉 평균 구하기
		double avgPay = 0;
		for(Jikwon j : jikwonList) {
			avgPay += j.getPay();
		}
		avgPay = Math.round((avgPay / jikwonList.size())*100) / 100.0; // 소수점 이하 세번째에서 반올림
		model.addAttribute("avg", avgPay);
		
		return "list";
	}
}