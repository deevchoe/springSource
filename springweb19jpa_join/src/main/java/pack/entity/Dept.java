package pack.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LazyInitializationException;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// 생성자로 주입할거니까 Setter는 필요없다
@Builder	// 생성자 인자를 메소드 체인을 통해 명시적으로 대입하여 생성자를 호출할 수 있게 빌더 클래스를 생성해준다. 멤버필드 중에서 선택적으로 생성자를 집어 넣을 때 매우 편리 이거 쓰려면 @AllArgsConstructor, @NoArgsConstructor 꼭 필요
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dept {	
	@Id
	private int deptno;
	private String dname;
	private String loc;
	
	// FetchType.LAZY : Dept 사용 중 Emp는 필요 할 때 지연 로딩
	// 세션이 열려있는 동안 세션관리가 필요하며 LazyInitializationException 조치 필요
	// FetchType.EAGER : Dept 사용 중 Emp는 필요 할 때 즉시 로딩 - 메모리 낭비가 좀 있다.
	// 하나의 Dept엔 여러개의 Emp가 들어올 수 있어
	@OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)// 관리하기 귀찮아!하면 EAGER 써 메모리를 많이 쓴다는 단점이 있지만 별도의 관리를 하지 않다도 되니 편리할수도 있는 방식이다
	@Builder.Default // Emp 엔티티가 생성될 때 list를 초기화 시켜준다.
	private List<Emp> list = new ArrayList<Emp>();
}
