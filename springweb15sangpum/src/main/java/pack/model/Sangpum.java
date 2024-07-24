package pack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sangdata")
public class Sangpum {
	@Id
	private int code;
	
	@Column(nullable = false)
	private String sang;
	
	private Integer su;
	private	int dan;
	/*
	 int:

기본형(Primitive Type)입니다.
null 값을 허용하지 않습니다.
값이 없을 때의 기본값은 0입니다.
Integer:

객체형(Class Type)입니다.
null 값을 허용합니다.
값이 없을 때는 null로 표현할 수 있습니다. 
	
	 */
}