package pack.gogek;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GogekMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("bankinit.xml");
		
		Gogek daniel = (Gogek)context.getBean("gogek");
		daniel.selectBank("shinhan");
		daniel.playInputMoney(500);
		daniel.playOutputMoney(200);
		System.out.print("daniel - ");
		daniel.showMoney();

		Gogek john = (Gogek)context.getBean("gogek");
		john.selectBank("shinhan");
		john.playInputMoney(500);
		john.playOutputMoney(200);
		System.out.print("john - ");
		john.showMoney();

		Gogek oscar = (Gogek)context.getBean("gogek");
		oscar.selectBank("woori");
		oscar.playInputMoney(500);
		oscar.playOutputMoney(100);
		System.out.print("oscar - ");
		oscar.showMoney();
		
		System.out.println("객체 주소 : " + daniel);
		System.out.println("객체 주소 : " + john);
		System.out.println("객체 주소 : " + oscar);
	}
}