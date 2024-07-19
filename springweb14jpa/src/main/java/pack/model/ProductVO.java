package pack.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="product")
public class ProductVO {
	
	// 어떤 데이터베이스던간에 동일
	
	@Id
	@Column(name="code")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// auto increment 번호 자동 증가..
	private int code;
	
	@Column(name="sang", nullable = true, length = 20)
	private String sang;
	
	private int su;
	private int dan;
}
