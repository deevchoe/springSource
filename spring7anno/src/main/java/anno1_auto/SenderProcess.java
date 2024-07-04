package anno1_auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// 참고 : 계층(Layers)별 어노테이션 구분
// Application layer : 클라이언트와 데이터 입출력을 제어 @Controller ...
// Domain layer : 어플리케이션 중심이며, 업무처리를 담당 @Service ...
// Infrastructor layer : DB에 대한 영속성(persistence) 등을 담당 @Repository ...

//@Component
@Service // 컴포넌트의 파생클래스로서 가독성을 위해서 만들어졌다. 서비스를 하려고 만든 클래스구나!하고 더 가독성이 좋아져서 이걸 권장한다.
//@Service("senderProcess")
//@Scope("singleton")
public class SenderProcess {
	// @Autowired : Bean의 자동 삽입을 위해 사용하는 어노테이션. (name에 의한 매핑이 아닌!!! type 으로 매핑)
	
	@Autowired
	private Sender sender;	// field injection : 간단하나 테스트 불편. 그러나 주로 사용 // component로 만들어진 sender 객체의 주소가 멤버 변수에 들어감
	
	/*
	@Autowired	// setter injection : setter를 계속 써줘야해서 코드가 장황해진다.
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	
	@Autowired	// constructor injection : 불변성과 테스트가 편하지만 생성자가 너무 많아진다.
	public SenderProcess(Sender sender) {
		this.sender = sender;
	}
	*/
	
	public Sender getSender() {
		return sender;
	}
	
	public void dispData() {
		sender.show();
	}
}
