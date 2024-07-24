package pack.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.stereotype.Repository;

@Repository
public class DataImpl implements DataInter{
	
	@Override
	public List<GogekDto> selectDataAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		List<GogekDto> list = em.createQuery("select g from GogekDto as g", GogekDto.class).getResultList();
		
		em.close();
		emf.close();
		return list;	
	}

}
