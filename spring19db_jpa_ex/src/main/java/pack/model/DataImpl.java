package pack.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.stereotype.Repository;

@Repository
public class DataImpl implements DataInter{
	
	@Override
	public List<JikwonDto> selectAllJikwon() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		List<JikwonDto> list = em.createQuery("select j from JikwonDto as j", JikwonDto.class).getResultList();
		
		em.close();
		emf.close();
		return list;	
	}
	
	@Override
	public List<Object[]> selectCount() {	// Object[]를 사용하는 이유 : JPA 쿼리 결과가 여러 타입의 값을 반환할 때 유연하게 처리할 수 있기 때문. 배열의 각 요소에 접근할 때 적절한 타입으로 캐스팅하여 사용할 수 있다.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();
		
		// 2개의 인덱스(부서번호, 직원 수)를 갖는 🌟배열🌟을 담은 List
		List<Object[]> result = em.createQuery("select j.buser_num, count(j.jikwon_no) FROM JikwonDto as j group by j.buser_num", Object[].class).getResultList();
		
		em.close();
		emf.close();
		return result;
	}

}
