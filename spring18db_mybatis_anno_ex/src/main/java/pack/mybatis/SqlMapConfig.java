package pack.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pack.model.SqlMapperInter;

public class SqlMapConfig {	// MyBatis 설정 파일을 읽어온다. 설정 파일에는 데이터베이스 연결 정보와 MyBatis가 사용할 매퍼에 대한 정보가 들어있다.
	public static SqlSessionFactory sqlSessionFactory;  // MyBatis가 SQL 명령을 실행하는데 필요한 객체.
	 
	  static{
	     String resource = "pack/mybatis/Configuration.xml";	// MyBatis 설정 파일의 경로가 지정됨. 이 설정 파일은 MyBatis가 데이터에비스와의 연결을 설정하고 매핑 정보를 로드하는데 사용됨
	     try {
	         Reader reader = Resources.getResourceAsReader(resource);
	         sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);	// SqlSessionFactoryBuilder를 사용하여 설정 파일을 기반으로 sqlSessionFactory를 생성
	         reader.close();
	         
	         // MyBatis Annotation 사용 시 추가
	         Class[] mappers = { SqlMapperInter.class };	// SqlMapperInter와 같은 인터페이스를 배열로 받아서 MyBatis에 추가
	         for(Class cl:mappers) {	 // 클래스 타입.
	        	 sqlSessionFactory.getConfiguration().addMapper(cl);
	         }
	     } catch (Exception e) {
	     System.out.println("SqlMapConfig 오류 : " + e);
	  }
	}
	 
	public static SqlSessionFactory getSqlSession(){	// sqlSessionFactory를 반환하여 다른 클래스에서 사용할 수 있게 함
	     return sqlSessionFactory;
	  }
}
