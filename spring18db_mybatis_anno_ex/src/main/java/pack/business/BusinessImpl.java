package pack.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pack.model.JikwonDto;
import pack.model.JikwonInter;

@Service
public class BusinessImpl implements BusinessInter {
	// model 클래스를 호출
	
	@Autowired
	private JikwonInter inter;

	@Override
	public void dataPrint() {
		List<JikwonDto> list = inter.selectDataAll();
		
		// console로 출력
		for(JikwonDto s:list) {
			System.out.println(s.getJikwon_no() + " " +
					s.getJikwon_name() + " " +
					s.getBuser_name() + " " +
					s.getJikwon_ibsail());
		}
	}

	@Override
	public void dataCount() {
		List<JikwonDto> list = inter.selectBuser();
		
		// console로 출력
		for(JikwonDto sa:list) {
			System.out.println(sa.getBuser_name() + " " +
					sa.getCnt());
		}
	}

	@Override
	public void dataMaxPay() {
		List<JikwonDto> list = inter.selectMaxpay();
		
		// console로 출력
		for(JikwonDto sab:list) {
			System.out.println(sab.getBuser_name() + " : " +
					sab.getJikwon_name() + " " +
					"(" + sab.getJikwon_pay() + ")");
		}	
	}
}
