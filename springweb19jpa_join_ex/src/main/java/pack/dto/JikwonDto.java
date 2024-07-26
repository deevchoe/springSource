package pack.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pack.entity.Jikwon;
import pack.entity.Gogek;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JikwonDto {
	// 사번, 이름, 직급, 부서명, 관리고객여부
	private int jno;
	
	private String jname;
	
	private String jik;
	
	private String bname;
	
	private String hasGogek;
	
	public static JikwonDto toDto(Jikwon jikwon) {
		// Entity to DTO
		return JikwonDto.builder()
				.jno(jikwon.getJno())
				.jname(jikwon.getJname())
				.jik(jikwon.getJik())
				.bname(jikwon.getBuser().getBname())
				.hasGogek((jikwon.getGogekList().size() > 0) ? "O" : "X")
				.build();
	}
}
