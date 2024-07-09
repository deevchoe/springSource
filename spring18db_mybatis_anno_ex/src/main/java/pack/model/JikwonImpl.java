package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import pack.mybatis.SqlMapConfig;

@Repository
public class JikwonImpl implements JikwonInter{
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();
	//SqlSessionFactory 객체를 초기화합니다. 이 객체는 MyBatis와 데이터베이스 간의 연결을 설정하고 관리합니다.
	//SqlMapConfig.getSqlSession() 메서드를 통해 SqlSessionFactory 객체를 얻습니다.
	
    @Override
    public List<JikwonDto> selectDataAll() {
        SqlSession sqlSession = factory.openSession();  // 새로운 SqlSession을 열어 데이터베이스와 연결합니다.
        List<JikwonDto> list = null;
        
        try {
            SqlMapperInter mapperInter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
            list = mapperInter.selectDataAll();  // 매퍼 인터페이스의 메서드를 호출하여 데이터 조회
        } catch (Exception e) {
            System.out.println("selectDataAll err : " + e);
        } finally {
            if(sqlSession != null) sqlSession.close();  // SqlSession을 닫아 자원 해제
        }
        
        return list;  // 조회된 데이터 리스트 반환
    }

	@Override
	public List<JikwonDto> selectBuser() {
		SqlSession sqlSession = factory.openSession();
		List<JikwonDto> list = null;
		
		try {
			SqlMapperInter mapperInter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			list = mapperInter.selectBuser();
		} catch (Exception e) {
			System.out.println("selectBuser err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}

	@Override
	public List<JikwonDto> selectMaxpay() {
		SqlSession sqlSession = factory.openSession();
		List<JikwonDto> list = null;
		
		try {
			SqlMapperInter mapperInter = (SqlMapperInter)sqlSession.getMapper(SqlMapperInter.class);
			list = mapperInter.selectMaxpay();
		} catch (Exception e) {
			System.out.println("selectDataAll err : " + e);
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		
		return list;
	}
}
