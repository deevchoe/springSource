package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SangpumRepository extends JpaRepository<Sangpum, Integer>{
	// 검색
	// 메소드 네이밍 룰 : findBy(엔티티명)칼람명..., readBy(엔티티명)칼람명..., getBy(엔티티명)칼람명...
	// findBy변수명And변수명, findBy변수명Or변수명, findBy변수명 GreaterThanEqual(크거나 같다)... 등 
	
	List<Sangpum> findBySangContaining(String svalue);	// 검색어가 포함된 : SQL에서 => like '%검색어%'      findBy칼람명...
	List<Sangpum> findBySangStartingWith(String svalue);	// 검색어로 시작되는 : SQL에서 => like '검색어%'
	List<Sangpum> findBySangEndingWith(String svalue);	// 검색어로 끝나는 : SQL에서 => like '%검색어'

	// JPQL 사용 : 이건 룰이 없다.
	@Query(value = "select s from Sangpum as s where s.sang like %:svalue%")	// 이름에 대한 매핑
	List<Sangpum> searchLike(@Param("svalue") String svalue);
}
