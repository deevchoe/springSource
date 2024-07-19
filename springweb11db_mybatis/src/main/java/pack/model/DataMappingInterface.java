package pack.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import pack.controller.FormBean;

@Mapper
public interface DataMappingInterface {
	
	@Select("select * from sangdata")
	List<DataDto> selectAll();
	
	@Select("select code, sang, su, dan from sangdata where sang like concat('%',#{searchValue},'%')")
	//@Select("select code, sang, su, dan from sangdata where sang like '%'||#{searchValue}||'%'")
	List<DataDto> selectSearch(FormBean bean);
}
