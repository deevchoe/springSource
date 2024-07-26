package pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.entity.Gogek;
import pack.entity.Jikwon;

public interface GogekRepository extends JpaRepository<Gogek, Integer>{

	// query method
    List<Gogek> findByJikwonJno(int jno);
}
