package pack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		// aop 없이 핵심 로직만 있는 상태
		ApplicationContext context = new ClassPathXmlApplicationContext("aopinit.xml");
		
		LogicInter inter = (LogicInter)context.getBean("logicImpl");
		inter.selectDataProcess1();
		inter.selectDataProcess2();
		
		
		
	}
}
