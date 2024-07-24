package pack.controller;

import lombok.Data;

@Data
public class FormBean {
	private int jikwon_no;
	private String jikwon_name, jikwon_gen, jikwon_pay, jikwon_jik;
	private String searchValue;
}
