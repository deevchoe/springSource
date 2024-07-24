package pack.entity;

import java.util.Date;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//생성자로 주입할거니까 Setter는 필요없다
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emp {	// 직원들						// 다대일 관계가 되겠지
	@Id
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;	// 관리자 직원번호
	@Temporal(TemporalType.DATE)	// jpa가 지원. 날짜 타입 매핑시 사용
	private Date hiredate;	// 입사일
	private Double sal;		// 월급
	private Double comm;
	//private Integer deptno;
	
	// Emp는 Dept를 참조해야해
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptno", foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))	// 외래키 제약조건을 비활성화 시킬 수 있다.
	private Dept dept;
}