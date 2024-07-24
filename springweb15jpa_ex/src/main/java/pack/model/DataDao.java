package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j		// 로그 출력 : 롬복이 지원.
public class DataDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JikwonRepository repository;
	
	// 전체 자료 읽기
	public List<JikwonEntity> getDataAll(){
		List<JikwonEntity> list = repository.findAll();	// jpa가 기본적으로 지원하는 메소드
		return list;
	}
	
	// 검색 자료 읽기
	public List<JikwonEntity> getDataSearch(String jvalue) {
		List<JikwonEntity> list = repository.findByJikContaining(jvalue);
		System.out.println("list : " + list.size());
		return list;
	}
}
