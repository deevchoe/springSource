package pack.model;

import java.util.List;

public interface DataInter {
	List<JikwonDto> selectAllJikwon();	// 전체 직원 정보
	List<Object[]> selectCount(); // 전체 부서 반환   Object가 슈퍼니까..(String이랑 int를 다 갖고있는...)
}