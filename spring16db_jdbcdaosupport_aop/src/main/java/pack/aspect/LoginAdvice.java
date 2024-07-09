package pack.aspect;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginAdvice {	// 관심사항
	@Around("execution(public void jikwonList())")// 누가 대상이야! BusinessImpl의 jikwonList()가 대상이야
	public Object haha(ProceedingJoinPoint joinPoint) throws Throwable{
		System.out.print("로그인 아이디 입력:");
		Scanner scanner = new Scanner(System.in);
		String id = scanner.next();
		
		if(!id.equalsIgnoreCase("kor")) {
			System.out.println("id 불일치. 로그인 실패");
			return null; 	// 로그인 실패했으니 핵심 로직을 보여주지 않는다.
		}
		
		Object object = joinPoint.proceed();	// jikwonList()가 수행되는 곳.
		scanner.close();
		
		return object;
	}
}
