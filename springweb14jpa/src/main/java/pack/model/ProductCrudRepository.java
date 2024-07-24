package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductCrudRepository extends JpaRepository<ProductVO, Integer>{	// <Entity name, pk의 타입>
	//CrudRepository > PagingAndSortingRepository > JpaRepository(CrudRepository와 PagingAndSortingRepository 이 가진 기능들을 다 가질 수 있다.)   

	// 메소드 이름으로 쿼리 생성. 생성 방법 : find + (엔티티 이름) + By + 변수 이름
	// 엔티티의 이름은 생략이 가능
	ProductVO findByCode(Integer code);
	
	// JPQL
	@Query(value = "select p from ProductVO as p")
	List<ProductVO> findAllData();
	

	// where 조건
	@Query(value = "select p from ProductVO as p where p.code=:code")	// 이름 기반
	ProductVO findByCodeMy(@Param("code") int code);	// 이름에 의한 매핑일 때는 @Param
	

	@Query(value = "select p from ProductVO as p where p.code=?1")		// 순서 기반
	ProductVO findByCodeMy2(int code);

	@Query(value = "select p from ProductVO as p where p.code=?1 or p.sang=?2")
	List<ProductVO> findByData(int code, String sang);
	
	// native Query문 사용
	@Query(value = "select code, sang, su, dan from product where code <= 5", nativeQuery = true)	// 기본값은 false
	List<ProductVO> findAllData2();
}
