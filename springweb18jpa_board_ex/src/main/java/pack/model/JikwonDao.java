package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JikwonDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataRepository dataRepository;
	
	public List<Jikwon> list(int searchBuser, String searchRating){
		List<Jikwon> jikwonList = null;
		try {
			if(searchRating.equals("all")) {
				jikwonList = dataRepository.findByNum(searchBuser);
			} else {				
				jikwonList = dataRepository.findByNumAndRating(searchBuser, searchRating);
			}
		} catch (Exception e) {
			logger.info("list err : " + e);
		}
		return jikwonList;
	}
}
