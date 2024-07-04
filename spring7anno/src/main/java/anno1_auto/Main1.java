package anno1_auto;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "anno1_auto")
// scan을 해야 객체가 만들어져. 어노테이션만한다고 객체 안만들어진다~~ 최상위 하나만 스캔하면 하위 패키지 애들은 다 스캔됨   어노테이션이 있는 클래스들을 찾아가서 인스턴스해준다.
public class Main1 {

	public static void main(String[] args) {
		// @Autowired에 대한 메인
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main1.class);
		SenderProcess process = context.getBean("senderProcess", SenderProcess.class);
		process.dispData();

	}

}
