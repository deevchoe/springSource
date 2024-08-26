package pack;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {	// 얘는 엔티티~ Dto는 생략할거야~
	
	// 영속적인
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)	// num 칼람이 auto_increment일 때
	private int num;
	private String name;
	private String tel;
	private String job;
	
	@Lob	// BLOB, CLOB 타입으로 처리됨
	private byte[] image;
	private String imagetype;

	// 비영속적인
	@Transient		// DB와 관련 없는 임시 데이터 저장용
	private String base64Image;	// base64로 인코딩된 이미지 타입
}
