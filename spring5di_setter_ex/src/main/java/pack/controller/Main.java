package pack.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("init.xml");
		
		// myImpl이라는 이름의 빈(bean)을 가져와서 MyInter 타입으로 변환
		MyInter myInter = (MyInter)context.getBean("myImpl");
		myInter.inputData();
		myInter.showData();

	}
}
