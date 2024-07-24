package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JikwonRepository extends JpaRepository<JikwonEntity, Integer>{

	List<JikwonEntity> findByJikContaining(String jvalue);	// 검색어가 포함된 : SQL에서 => like '%검색어%'      findBy칼람명...
}
