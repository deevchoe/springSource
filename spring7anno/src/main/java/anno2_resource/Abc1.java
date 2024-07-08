package anno2_resource;

import org.springframework.stereotype.Component;

@Component
public class Abc1 {	// POJO --> 단순하게 멤버필드와 겟터셋터만 갖고 있는거
	//자바에서 제공하는 기능만 사용하기 때문에 해당 클래스는 Java 언어 이외의 특정한 기술에 종속되어 있지 않은 순수한 객체이기 때문에 POJO라고 부를 수 있다.
	//출처: https://ittrue.tistory.com/211 [IT is True:티스토리]
	
	private String irum;
	
	public void setIrum(String irum) {
		this.irum = irum;
	}
	
	public String getIrum() {
		return irum;
	}
}
