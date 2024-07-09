package pack;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect	// 인스턴스가 안돼. 그래서 컴포넌트도 같이 줘야해
@Component
public class OurAdvice {	// Aspect 클래스 : 관심사항을 담아놓은 클래스. Advice용
	@Around	("execution(* *..*LogicInter*.*(..)) or execution(public void selectAll())")		// 핵심메소드 앞뒤에 수행
	public Object kbs(ProceedingJoinPoint joinPoint) throws Throwable{
		// 수행 시간 체크
		StopWatch stopWatch = new StopWatch(); // 스탑워치 객체가 만들어졌넹
		stopWatch.start();
		
		System.out.println("핵심 메소드 수행 전 관심사항 실행");
		// 코드가 길어지면 별도의 클래스를 만들어놓고 클래스의 포함관계를 이용해서 사용하면 된다.
		Object object = joinPoint.proceed();	// 선택된 핵심 메소드 수행
		
		System.out.println("핵심 메소드 수행 후 뭔가를 실행");
		
		stopWatch.stop();
		System.out.println("처리 시간 : " + stopWatch.getTotalTimeSeconds());
		return object;
	}
}
