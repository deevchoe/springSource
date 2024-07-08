package pack.gogek;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import pack.bank.Bank;
import pack.bank.ShinhanBank;
import pack.bank.WooriBank;

@Service
@ComponentScan("pack.bank")
@Scope("prototype")
public class Gogek {	// 고객이 bank를 스캔했다!
	private Bank bank;
	
	@Autowired(required = true)	// type으로 연결. false=신한 뱅크 객체가 있으면 사용하고 없으면 매핑 안할거야ㅏ   true= 신한 뱅크 객체가 없으면 에러 떨어져.
	private ShinhanBank shinhanBank;
	
	@Resource(name="wooriBank")	// name으로 연결
	private WooriBank wooriBank;
	
	public void selectBank(String sel) {
		if(sel.equals("shinhan")) {
			bank = shinhanBank;
		}else if(sel.equals("woori")){
			bank = wooriBank;
		}
	}
	
	public void playInputMoney(int money) {
		bank.inputMoney(money); 	// 이때 bank는 신한일수도 우리일수도 있다.
	}

	public void playOutputMoney(int money) {
		bank.outputMoney(money);
	}
	
	private String msg;
	
	@PostConstruct	// 생성자 처리 후 자동 호출 : 초기화 작업 가능
	public void abc() {
		msg ="계좌 잔고 : ";
	}
	
	@PreDestroy		// 웹서비스 종료 직전 자동 호출 : 마무리 작업 가능
	public void def() {
		if(shinhanBank != null) shinhanBank = null;
		if(wooriBank != null) wooriBank = null;
	}
	
	public void showMoney() {
		//System.out.println("계좌 잔고 : " + bank.getMoney());	// 신한의 getMoney 일수도 우리의 getMoney일수도 있다.
		System.out.println(msg + bank.getMoney());
	}
}