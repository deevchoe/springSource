package pack.business;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pack.model.JikwonDto;
import pack.model.JikwonInter;

@Service
public class BusinessImpl implements BusinessInter {
	// 모델을 포함 관계로 호출
	@Autowired
	private JikwonInter jikwonInter;
	
	@Override
	public void selectProcess() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("고객 번호 입력: ");
		String gogekNum = scanner.nextLine();
		
		System.out.print("고객 이름 입력: ");
		String gogekName = scanner.nextLine();
		
		scanner.close();

		List<JikwonDto> list = jikwonInter.selectList(gogekNum, gogekName);
		if(list.isEmpty()) {
			System.out.println("로그인 실패");
			System.exit(0);
		}
		

		
		for(JikwonDto j:list) {
			System.out.println(j.getJikwon_name() + " " + 
							j.getJikwon_jik() + " "+
							j.getJikwon_gen());
			
		}
				
	}
}