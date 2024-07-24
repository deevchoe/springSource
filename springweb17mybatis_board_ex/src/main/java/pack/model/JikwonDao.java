package pack.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.JikwonBean;

@Repository
public class JikwonDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataMapInterface mapInterface;
	
	public List<Jikwon> searchAll(JikwonBean bean){
		List<Jikwon> list = mapInterface.selectAll(bean);
		return list;
	}
	public List<Jikwon> search(JikwonBean bean){
		List<Jikwon> list = mapInterface.selectSearch(bean);
		return list;
	}
}
