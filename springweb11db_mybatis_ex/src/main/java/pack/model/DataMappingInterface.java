package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataMappingInterface {
	
	@Select("select * from jikwon where jikwon_jik = #{jikwon_jik}")
	List<DataDto> selectSearch(String jikwon_jik);
}
