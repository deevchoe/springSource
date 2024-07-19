package pack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pack.model.ProductCrudRepository;
import pack.model.ProductVO;

@SpringBootApplication
public class Springweb14jpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springweb14jpaApplication.class, args)
			.getBean(Springweb14jpaApplication.class).execute();
	}
	
	@Autowired
	ProductCrudRepository repository;
	
	private void execute() {
		System.out.println("execute");
		
		//insertData();
		selectData();
	}
	
	private void insertData() {
		/*
		ProductVO productVO = new ProductVO();
		productVO.setSang("볼펜");
		productVO.setSu(3);
		productVO.setDan(1000);
		productVO = repository.save(productVO);
		System.out.println("등록 데이터 : " + productVO);
		*/
		ProductVO productVO = new ProductVO();
		productVO.setCode(1);
		productVO.setSang("지우개");
		productVO.setSu(3);
		productVO.setDan(500);
		productVO = repository.save(productVO);
		
	}
	
	private void selectData() {
		System.out.println("전체 자료 읽기 : DBMS에 독립적이다.");
		List<ProductVO> list = repository.findAll();
		
		for(ProductVO vo:list) {
			System.out.println(vo.getCode() + " " + 
					vo.getSang() + " " +
					vo.getSu() + " " +
					vo.getDan());
		}
		
		System.out.println("부분 자료 읽기");
		ProductVO vo = repository.findById(1).get();
		System.out.println(vo.getCode() + " " + 
				vo.getSang() + " " +
				vo.getSu() + " " +
				vo.getDan());
		
		System.out.println();
		ProductVO vo3 = repository.findByCode(2);	// 메소드 이름으로 쿼리 생성
		System.out.println(vo3.getCode() + " " + 
				vo3.getSang() + " " +
				vo3.getSu() + " " +
				vo3.getDan());
		
		System.out.println("---JPQL 사용-----------");
		List<ProductVO> list2 = repository.findAllData();
		
		for(ProductVO vo2:list2) {
			System.out.println(vo.getCode() + " " + 
					vo2.getSang() + " " +
					vo2.getSu() + " " +
					vo2.getDan());
		}
		
		System.out.println("부분 자료 읽기 - JPQL");
		ProductVO vo4 = repository.findByCodeMy(1);	// 메소드 이름 임의 생성(이름 기반)
		System.out.println(vo4.getCode() + " " + 
				vo4.getSang() + " " +
				vo4.getSu() + " " +
				vo4.getDan());

		ProductVO vo5 = repository.findByCodeMy2(1);	// 메소드 이름 임의 생성(순서 기반)
		System.out.println(vo4.getCode() + " " + 
				vo5.getSang() + " " +
				vo5.getSu() + " " +
				vo5.getDan());
		
		System.out.println();
		List<ProductVO> list3 = repository.findByData(1, "초코파이");
		
		for(ProductVO vo6:list3) {
			System.out.println(vo6.getCode() + " " + 
					vo6.getSang() + " " +
					vo6.getSu() + " " +
					vo6.getDan());
		}
	}
}
