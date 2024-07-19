package pack;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import other.OtherClass;

@SpringBootApplication
@ComponentScan(basePackages = {"other"})
public class Springweb13jpaBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springweb13jpaBasicApplication.class, args)
				.getBean(Springweb13jpaBasicApplication.class).myExecute();
	}
	
	@Autowired
	ProductCrudRepository crudRepository;
	
	@Autowired
	OtherClass class1;
	
	private void myExecute() {
		System.out.println("독립적인 프로그램으로 실행");
		
		insData();	// 추가 또는 수정
		delData();	// 삭제
		selectData();	// 목록 보기
		
		class1.abc();
	}
	
	private void insData() {
		//ProductVO productVO = new ProductVO();
		//productVO.setCode(4);
		// ...
		//ProductVO productVO = new ProductVO(null, "볼펜", 2, 1000);
		
		ProductVO productVO = new ProductVO(2, "사랑비", 1, 700);
		//System.out.println(productVO.toString());
		crudRepository.save(productVO);	// 번호를 안주거나 없는 번호면 insert, 있는 번호에 대한 데이터는 update를 수행 
	}
	
	private void delData() {
		crudRepository.deleteById(5);	// 있으면 지우고 없으면 통과. 에러 안떨어짐.
	}
	
	private void selectData() {
		// 전체 레코드 읽기
		List<ProductVO> list = (List<ProductVO>)crudRepository.findAll(); // select * from product이 내부적으로 이루어짐. CrudRepository가 지원한다.
		//System.out.println(list.get(0).getSang());
		for(ProductVO p:list) {
			System.out.println(p.getCode() + " " +
					p.getSang() + " " +
					p.getSu() + " " +
					p.getDan());
		}
		
		System.out.println();
		// 1개 레코드 읽기
		ProductVO vo = crudRepository.findById(2).get();
		System.out.println(vo.getCode() + " " +
				vo.getSang() + " " +
				vo.getSu() + " " +
				vo.getDan());
	}

}
