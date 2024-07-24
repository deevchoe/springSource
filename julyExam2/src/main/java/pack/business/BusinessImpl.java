package pack.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pack.model.GogekDto;
import pack.model.DataInter;

@Service
public class BusinessImpl implements BusinessInter {

	@Autowired
	private DataInter inter;

	public void printGogek() {
		List<GogekDto> glist = inter.selectDataAll();
		System.out.println("🐻‍❄️ 고객 정보 🐻");
		for(GogekDto g : glist) {
			System.out.println(g.getGogek_no() + " " + g.getGogek_name() + " " + g.getGogek_tel());
		}
	}
}
