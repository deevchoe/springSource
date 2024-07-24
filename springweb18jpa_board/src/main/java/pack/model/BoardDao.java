package pack.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pack.controller.BoardBean;

@Repository
public class BoardDao {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataRepository dataRepository;
	
	public List<Board> list(){	// 전체 자료 읽기
		List<Board> list = dataRepository.findAll();
		logger.info("list size : " + list.size());
		
		return list;
	}

	public List<Board> search(BoardBean bean){	// 검색 자료 읽기
		List<Board> slist = null;
		
		if(bean.getSearchName().equals("author")) {
			slist = dataRepository.searchLike(bean.getSearchValue());
		}else {
			slist = dataRepository.searchLike2(bean.getSearchValue());			
		}
		
		return slist;
	}
	
	@Transactional		// 프록시 객체는 해당 메소드가 처리될 때 Commit or Rollback을 알아서 수행
	// CheckedException 또는 예외가 없는 경우 Commit을 수행
	// UncheckedException이 발생하면 Rollback 수행
	// commit이나 rollback을 우리가 직접 적어줄 필요가 없다
	public String insertData(BoardBean bean) {
		try {
			// 새글 입력 시 가장 큰번호를 얻어 +1
			int max = dataRepository.maxNum();
			
			Board dto = new Board();
			dto.setNum(max + 1);
			dto.setAuthor(bean.getAuthor());
			dto.setTitle(bean.getTitle());
			dto.setContent(bean.getContent());
			dto.setBwrite(Timestamp.valueOf(LocalDateTime.now()));
			dto.setReadcnt(0);
			
			dataRepository.save(dto);
			return "success";
		} catch (Exception e) {
			return "입력 오류 : " + e.getMessage();
		}
	}
	
	@Transactional	// select가 아닌 모든 곳에는 걸어주면 편리~
	public Board detail(int num) {
		// 조회수 증가
		dataRepository.updateReadcnt(num);
		
		// Optional<T> 클래스를 사용해 NPE를 방지할 수 있도록 도와준다.
		// Spring Data JPA를 사용할 때 Repository에서 findById()의 반환값은 Optional 타입
		Optional<Board> board = dataRepository.findById(num);
		logger.info("board :: {}", board.get());
		
		if(board.isPresent()) {	// board가 있니?
			return board.get();
		}else {
			return new Board();
		}
	}
	
	@Transactional
	public String updateData(BoardBean bean) {
		try {
			// 조회수도 수정에 참여하기 위한 선행 작업
			Optional<Board> board = dataRepository.findById(bean.getNum());
			Board imsi = board.get();
			
			/*
			Board dto = new Board();
			dto.setNum(bean.getNum());	// 이미 등록된 num이므로 수정이 될 것.
			dto.setAuthor(bean.getAuthor());
			dto.setTitle(bean.getTitle());
			dto.setContent(bean.getContent());
			dto.setReadcnt(imsi.getReadcnt());
			
			dataRepository.save(dto);
			*/
			
			imsi.setAuthor(bean.getAuthor());
			imsi.setTitle(bean.getTitle());
			imsi.setContent(bean.getContent());
			imsi.setReadcnt(imsi.getReadcnt());
			
			return "success";
		} catch (Exception e) {
			return "수정 오류 : " + e.getMessage();
		}
	}

	@Transactional
	public String deleteData(int num) {
		try {
			dataRepository.deleteById(num);
			
			return "success";
		} catch (Exception e) {
			return "삭제 오류 : " + e.getMessage();
		}
	}
}
