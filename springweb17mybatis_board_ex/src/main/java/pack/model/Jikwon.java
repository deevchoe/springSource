package pack.model;

import lombok.Data;

@Data
public class Jikwon { // 실제 DB랑 매핑을 위한...   Entity
	private int jikwon_no, buser_no, avg;
	private String jikwon_name, jikwon_jik, jikwon_pay, jikwon_rating, buser_name;
	private String searchName, grade;
}
