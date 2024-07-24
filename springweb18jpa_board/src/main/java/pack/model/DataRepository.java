package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DataRepository extends JpaRepository<Board, Integer>{
	// JPQL
	// 검색용
	@Query("select b from Board as b where b.author like %?1%")	// 위치에 대한 매핑. 이름이 포함된 이때 물음표는 하나 searchValue!!
	List<Board> searchLike(String searchValue);

	@Query("select b from Board b where b.title like %:searchValue%")	// 이름에 대한 매핑
	List<Board> searchLike2(@Param("searchValue") String searchValue);
	
	// 추가할 때 가장 큰 번호 얻기
	@Query("select max(b.num) from Board b")
	int maxNum();	// 여기서 +1을 하면 새 글 번호를 얻을 수 있겠지!
	
	// 상세보기 할 때 조회수 증가
	// 내부적으로 JPA는 벌크 연산을 한다(update,delete, insert). 
	//영속성 컨텍스트에 있는 Board와 DB에 있는 Board 값이 다를 수 있다.
	// 벌크 연산 수행 후 영속성 컨텍스트에 있는 쿼리를 refresh(clear) 해야한다.
	@Modifying(clearAutomatically = true)	// 1차 캐시를 비워주는 설정. 영속성 컨텍스트에 있는 쿼리를 초기화.       한마디로 이거 하나 써주면 된다.
	@Query(value = "update Board as bo set bo.readcnt=bo.readcnt+1 where bo.num=?1")	// value는 써도 되고 안써도되고~
	void updateReadcnt(int num);
	
}
