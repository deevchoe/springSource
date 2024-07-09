package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import pack.mybatis.SqlMapConfig;

@Repository
public class SangpumImpl implements SangpumInter{
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	//SqlSessionFactory 객체를 초기화합니다. 이 객체는 MyBatis와 데이터베이스 간의 연결을 설정하고 관리합니다.
	//SqlMapConfig.getSqlSession() 메서드를 통해 SqlSessionFactory 객체를 얻습니다.
	
	@Override
	public List<SangpumDto> selectDataAll() {
		SqlSession sqlSession = factory.openSession();
		List<SangpumDto> list = null;
		
		try {
			SqlMapperInter mapperInter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			list = mapperInter.selectDataAll();
		} catch (Exception e) {
			System.out.println("selectDataAll err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}
}
