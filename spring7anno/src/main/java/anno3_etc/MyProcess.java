package anno3_etc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("my")
public class MyProcess {
	// @Value : 변수에 값을 초기화하기 위해 사용 할수도 있다.
	// Spring EL : #{표현식}, 만들어진 Component 객체를 이용, private는 getter를 이용해서 가져옴
	// SpEL 표현식은 # 기호로 시작하며 중괄호로 묶어서 표현한다. #{표현식}
	// 속성 값을 참조할 때는 $ 기호와 중괄호로 묶어서 표현한다. ${property.name}  객체변수.멤버변수명
	@Value("#{dataInfo.name}")	
	private String name;
	
	private String part;
	
	@Autowired
	//public MyProcess(@Value("영업부") String part) {	// 생성자를 통해 영업부가 들어감
	public MyProcess(@Value("#{dataInfo.part}") String part) {	// dataInfo 객체의 part를 읽어와서 part에게 줌
		// 생성자
		this.part = part;
	}
	
	@Value("123")	// Value annotation으로 값을 주면 기본이 String type이다. 그래서 따옴표를 빼면 type mismatch 에러가 떨어짐
	private int age;
	
	@Value("1,2,3,4")
	private int arr[];	// 배열의 초기치로 값이 들어감
	
	
	
	
	public void showData() {
		System.out.println("name : " + name);
		System.out.println("part : " + part);
		System.out.println("age : " + age);
		System.out.println(arr[0] + " " + arr[3]);
	}
}
