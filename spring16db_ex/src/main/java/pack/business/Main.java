package pack.business;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("init.xml"); // @Configuration 쓰면 안써도 됨
		
		BusinessInter inter = (BusinessInter)context.getBean("businessImpl");
		inter.selectProcess();
	}
}