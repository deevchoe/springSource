package pack.controller;

import java.util.ArrayList;
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
    public String searchJikwon(JikwonBean bean, Model model) {
        List<Jikwon> jikwonList = new ArrayList<>();

        // 평가등급에 따라 검색 쿼리를 다르게 실행
        if (bean.getSearchRating().equals("all")) {
            jikwonList = dao.searchAll(bean); // all을 선택했을 때 모든 결과를 검색
        } else {
            jikwonList = dao.search(bean); // 특정 평가등급으로 검색
        }

        model.addAttribute("list", jikwonList);

        return "list"; // 검색 결과를 보여줄 Thymeleaf 템플릿 이름
    }
}