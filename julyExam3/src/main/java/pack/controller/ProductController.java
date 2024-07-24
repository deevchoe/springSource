package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

	@GetMapping("checkPrice")
	public String abc1(Model model, @RequestParam(value="name") String name,@RequestParam(value="price") int price) {
		ItemDto itemDto = new ItemDto();
		itemDto.setName(name);
		itemDto.setPrice(price);
		
		if(price >= 50000) {
			itemDto.setData("고가");
		} else {
			itemDto.setData("저가");
		}
		model.addAttribute("itemDto", itemDto);
		
		return "result";
	}
}
