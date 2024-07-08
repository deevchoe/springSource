package anno2_resource;

import org.springframework.stereotype.Component;

@Component("aaa")	// 객체변수 이름은 abc2가 아닌 aaa가 된다.
public class Abc2 {
	private int nai;
	
	public void setNai(int nai) {
		this.nai = nai;
	}

	public int getNai() {
		return nai;
	}

}
