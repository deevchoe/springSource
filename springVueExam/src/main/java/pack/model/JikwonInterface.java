package pack.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.entity.JikwonEntity;

public interface JikwonInterface extends JpaRepository<JikwonEntity, String> {
	public List<JikwonEntity> findByJik(String jik);
	
}
