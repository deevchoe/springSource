package pack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import pack.dto.DeptDto;
import pack.dto.EmpListDto;
import pack.entity.Dept;
import pack.entity.Emp;
import pack.repository.DeptRepository;
import pack.repository.EmpRepository;

@Controller
public class EmployController {
	@Autowired
	private EntityManagerFactory factory;
	
	@Autowired
	private EmpRepository empRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	@GetMapping("/employ/list")
	public String emplist(Model model) {
		// 모든 직원의 정보 출력
		//List<Emp> list = empRepo.findAll();	// 기본 메소드
		//List<Emp> list = empRepo.findAllByOrderByEmpnoAsc();	// 메소드룰
		//List<Emp> list = empRepo.findAllByOrderByEmpnoDesc();	// 메소드룰
		List<Emp> list = empRepo.getListAll();	// JPQL
		//List<Emp> list = empRepo.getList(1500);	// 1,500을 초과하는 직원들만 보기
				
		model.addAttribute("list", list);
		return "employ/elist";
	}
	
	// 부서 정보 출력
	@GetMapping("/employ/dept")	
	public String emplist(@RequestParam("deptno")int deptno, Model model) {
		Dept d = deptRepo.findById(deptno).get();
		DeptDto dto = DeptDto.toDto(d);
		
		model.addAttribute("dto", dto);
		return "employ/dept";
	}

	// JPQL 연습장 관련
	@GetMapping("/jpql")
	public String jpql() {
		return "jpql";
	}
	
	@ResponseBody	// json으로 찍으려면 ResponseBody 써줘야해. 그럼 json 타입으로 리턴해줘
	@PostMapping("/jpql/test")	// 5. 서버에서 POST 요청 수신. 클라이언트에서 전송한 JPQL 쿼리를 실행하고, 결과를 JSON으로 반환
	public List<EmpListDto> test(@RequestParam("query")String query) {
		//System.out.println(query);	// select e from Emp as e
		EntityManager em = factory.createEntityManager();// EntityManager를 통해 JPQL 쿼리를 실행하고 결과를 가져온다.
		EntityTransaction tx = em.getTransaction();	// 쿼리 실행 후 EntityTransaction을 사용하여 트랜잭션을 관리한다.
		tx.begin(); // 트랜잭션 시작
		List<EmpListDto> list = null;	// 결과를 EmpListDto 객체로 변환하여 클라이언트에 반환한다.
		try {
			// 전달받은 query(JPQL 형식을 따른)문을 실행
			TypedQuery<Emp> tQuery = em.createQuery(query, Emp.class);	// 클라이언트에서 전달받은 JPQL 쿼리를 사용하여 TypedQuery를 생성
			
			list = tQuery.getResultStream().map(EmpListDto::toDto).toList();	// 쿼리 결과를 EmpListDto 객체로 변환하여 리스트로 만들어.    stream으로 넘어온 여러개의 값을 map을 이용해서 메소드로 실행.     여러개의 레코드를 하나씩 실행시키기 위해...
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();	// 에러를 콘솔 창에 찍어
			tx.rollback();
		}finally {
			em.close();
		}
		
		return list;
	}
}
