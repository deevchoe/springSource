package pack.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pack.entity.Dept;
import pack.entity.Emp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto {
	private int deptno;
	private String dname;
	private String loc;
	
	private int count;	// 근무 인원수 저장
	private List<String> names;	// 근무 직원의 이름들
	
	// Entity를 Dto로 변환하기
	public static DeptDto toDto(Dept dept) {
		// 직원명 저장 리스트
		List<String> names = new ArrayList<String>();
		for(Emp temp:dept.getList()) {	// 직원의 이름을 리스트에 누적
			names.add(temp.getEname());
		}
		
		// 생성자를 통해 값을 집어넣는데 .찍고 .찍고(메소드 체인을 이용) 집어넣고 싶은 값만 해주면 됨
		// 롬복 builder로 생성자 주입 값을 선택적으로 입력 가능. chain 사용
		// Entity를 Dto로 변환하기	// 변환하고 싶은 것만 하면돼. 이게 빌더의 장점
		return DeptDto.builder()
				.deptno(dept.getDeptno())
				.dname(dept.getDname())
				.loc(dept.getLoc())
				.count(dept.getList().size())
				.names(names)
				
				.build();
	}
}
