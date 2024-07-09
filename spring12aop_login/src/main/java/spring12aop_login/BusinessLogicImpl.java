package spring12aop_login;

import org.springframework.stereotype.Service;

@Service("bImpl")
public class BusinessLogicImpl implements BusinessLogicInter{
	public BusinessLogicImpl(){
		// 생성자
	}
	
	@Override
	public void startProcess() {
		System.out.println("검증이 됐으므로 핵심 로직 수행");
	}
}
