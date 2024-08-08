package pack.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.dto.MemberDto;

@Repository
public class MemberDao {
	@Autowired
	private SqlSession session;
	
	//회원 자료 전체 읽기
	public List<MemberDto> getList(){
		return session.selectList("member.getList");
	}
	
	public void insert(MemberDto dto) {
		session.insert("member.insert", dto);
	}
	
	public MemberDto getData(int num) {
		return session.selectOne("member.getData", num);
	}
	
	public void update(MemberDto dto) {
		session.update("member.update", dto);
	}

	public void delete(int num) {
		session.delete("member.delete", num);
	}
}
