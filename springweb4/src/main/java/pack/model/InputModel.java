package pack.model;

import org.springframework.stereotype.Service;

import pack.controller.InputBean;

@Service	//@Component도 괜차나 DB라고 한다면 persistance...
public class InputModel {
	public String computePrice(InputBean bean) {
		String data = "품명:" + bean.getSang() + " " +
					", 금액:" + (bean.getSu() * bean.getDan());
		return data;	// 컨트롤러에서 얘를 부르면 되겠지!!
	}
}
