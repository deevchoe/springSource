package spring12aop_login;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("init.xml"); // @Configuration 쓰면 안써도 됨
		BusinessLogicInter inter = (BusinessLogicInter)context.getBean("bImpl");	// 객체변수의 이름은 bImpl이야!
		inter.startProcess();
	}
}