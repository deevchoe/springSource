package pack.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputBean {
	private String sang;
	private int su;
	private int dan;	// 연산 안하고 db에 쏙 집어넣을거면 int를 쓸 이유가 없다. 연산을 할거면 int 써줘~
	
}
