package pack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("configuration.xml");
		OurProcess our = (OurProcess)context.getBean("our");
		System.out.println(our); // OurProcess에서 String으로 return하고 있으니까 찍어줘야징
	}

}
