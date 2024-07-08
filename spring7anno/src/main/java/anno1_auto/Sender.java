package anno1_auto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component	// single pattern으로 Sender 객체가 생성. 객체 변수명은 sender가 된다. => 자동으로 이루어진다.
// singleton의 객체가 만들어진다.
//@Component("sender")
//@Component("sen")	// 객체 변수 기본으로 안쓸래 sen으로 쓸거야하면 이렇게 써줘야함
//@Scope("singleton")
public class Sender implements SenderInter{	// implements함으로써 SenderInter 타입의 Sender 클래스가 되었다.
	public void show() {
		System.out.println("Sender의 show method 수행");
	}
}
