package pack;

import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<ProductVO, Integer>{
	// save(), findById(), count(), ... 지원 받음
	
}
