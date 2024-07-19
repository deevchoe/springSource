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
	private DataMappingInterface mappingInterface;
	
	public List<DataDto> getDataSearch(String jikwon_jik){
		List<DataDto> list = mappingInterface.selectSearch(jikwon_jik);
		System.out.println("list.size : " + list.size());
		logger.info("list.size : " + list.size());
		return list;
	}
}
