package pack.dto;

import java.util.Date;
import java.util.List;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class EmpListDto {
	private Integer empno;
	private String ename;
	private Integer deptno;
	private String dname;
	private String job;
	
	// Entity를 Dto로 변환하기	// 변환하고 싶은 것만 하면돼. 이게 빌더의 장점
	public static EmpListDto toDto(Emp emp) {
		return EmpListDto.builder()
				.empno(emp.getEmpno())
				.ename(emp.getEname())
				.deptno(emp.getDept().getDeptno())
				.dname(emp.getDept().getDname())
				.job(emp.getJob())
				.build();
	}
}
