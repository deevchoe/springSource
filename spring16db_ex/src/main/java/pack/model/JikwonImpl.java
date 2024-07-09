package pack.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository // 이 클래스가 데이터 액세스 객체(DAO)임을 나타낸다.
public class JikwonImpl extends JdbcDaoSupport implements JikwonInter {    // JikwonImpl 클래스는 JdbcDaoSupport를 상속받고, JikwonInter 인터페이스를 구현
    @Autowired  // @Autowired 어노테이션을 사용하여 DataSource 객체를 주입받는다.
    private DataSource dataSource;
    
    @PostConstruct
    public void abcd() {    // @PostConstruct 어노테이션이 붙은 abcd() 메소드는 생성자 호출 후에 실행되는 메소드
        setDataSource(dataSource); // setDataSource(dataSource)를 호출하여 JdbcDaoSupport의 데이터 소스를 설정한다.
    }
    
    @Override
    public List<JikwonDto> selectList(String gogekNum, String gogekName) throws DataAccessException {    // selectList 메소드는 고객 번호와 이름을 매개변수로 받아서 데이터베이스에서 직원 정보를 조회. RowMapper 인터페이스를 구현한 JikwonMapper를 사용하여 결과를 매핑한다.
        RowMapper rowMapper = new JikwonMapper(); // RowMapper 인터페이스를 구현한 JikwonMapper 클래스 사용
        String sql = "SELECT jikwon_name, jikwon_jik, jikwon_gen"
                + " FROM jikwon INNER JOIN gogek ON jikwon.jikwon_no = gogek.gogek_damsano where gogek_no = ? and gogek_name = ?";
        
        // SQL 쿼리를 실행하고 결과를 매핑하여 리스트로 반환.
        return getJdbcTemplate().query(sql, rowMapper, gogekNum, gogekName);
    }
    
    // RowMapper 인터페이스를 구현한 내부 클래스
    class JikwonMapper implements RowMapper {    // RowMapper 인터페이스를 구현하여 데이터베이스 결과를 JikwonDto 객체로 변환한다.
        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new JikwonDto() {
                {   // 익명 클래스를 사용하여 JikwonDto 객체를 초기화한다.
                    setJikwon_name(rs.getString("jikwon_name"));
                    setJikwon_jik(rs.getString("jikwon_jik"));
                    setJikwon_gen(rs.getString("jikwon_gen"));
                }
            };
        }
    }
}