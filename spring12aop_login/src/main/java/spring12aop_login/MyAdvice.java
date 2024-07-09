package spring12aop_login;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//관심로직
@Component
@Aspect
public class MyAdvice {
	@Around("execution(public void startProcess())")
	public Object abc(ProceedingJoinPoint joinPoint) throws Throwable{	// 메소드 이름이 무엇이든 파라미터는 고정.
		
		System.out.println("AOP 시작 : 핵심 로직 수행 전 ID 검증");
		
		System.out.print("input id : ");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.nextLine();
		scanner.close();
		
		if(!id.equals("ok")) {
			System.out.println("id 불일치! 작업을 종료합니다.");
			return null;	// null을 만나면 아래 있는 joinPoint를 못만나. 그냥 abc 메소드가 종료돼
		}
		
		Object object = joinPoint.proceed();
		
		return object;
	}
}
