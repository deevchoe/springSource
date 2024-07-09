package pack.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pack.model.SqlMapperInter;

public class SqlMapConfig {
	public static SqlSessionFactory sqlSessionFactory;  //DB의 SQL명령을 실행시킬 때 필요한 메소드를 갖고 있다.
	 
	  static{
	     String resource = "pack/mybatis/Configuration.xml";
	     try {
	         Reader reader = Resources.getResourceAsReader(resource);
	         sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	         reader.close();
	         
	         // MyBatis Annotation 사용 시 추가
	         Class[] mappers = { SqlMapperInter.class };	// 인터페이스가 여러개 있을 수 있으니 배열로 받아.
	         for(Class cl:mappers) {	 // 클래스 타입.
	        	 sqlSessionFactory.getConfiguration().addMapper(cl);
	         }
	     } catch (Exception e) {
	     System.out.println("SqlMapConfig 오류 : " + e);
	  }
	}
	 
	public static SqlSessionFactory getSqlSession(){
	     return sqlSessionFactory;
	  }
}
