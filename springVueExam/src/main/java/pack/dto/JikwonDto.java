package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pack.entity.JikwonEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JikwonDto {
	// 사번, 이름, 부서명, 직급, 연봉
	
	private String jno;
	
	private String jname;
	
	private String jik;
	
	private String bname;
	
	private String pay;
	
	public static JikwonDto toDto(JikwonEntity jikwon) {
		// Entity to DTO
		return JikwonDto.builder()
				.jno(jikwon.getJno())
				.jname(jikwon.getJname())
				.jik(jikwon.getJik())
				.bname(jikwon.getBuser().getBname())
				.pay(jikwon.getPay())
				.build();
	}
}