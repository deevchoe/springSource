package pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.transaction.Transactional;
import pack.dto.MemberDto;
import pack.entity.Member;
import pack.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberServiceInter{
	@Autowired
	private MemberRepository memRepository;
	
	@Override
	public void getList(Model model) {	// 이 model은 스프링에 의해 관리된다. 스프링에 의해 관리되는 전역 객체이다. 스프링이 관리해주기 때문에 void를 해줘도 괜찮다
		/*
		// 방법1 : member 전체 자료 반환 : 기본 메소드 사용
		// Member 엔티티를 MemberDto 객체로 전달
		List<Member> entityliList = memRepository.findAll();	// memberEntity가 들어가있지
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		for(Member temp:entityliList) {
			MemberDto dto = new MemberDto();
			dto.setNum(temp.getNum());	// memberDto가 들어있다
			dto.setName(temp.getName());
			dto.setAddr(temp.getAddr());
			list.add(dto);
		}
		*/
		
		// 방법2 : List<Member>를 Stream으로 변경해서 map()을 사용 List<MemberDto>로 변경하기
		List<MemberDto> list = memRepository
								.findAllByOrderByNumDesc()
								.stream()
								.map(item -> MemberDto.toDto(item)).toList();

		/*
		// 방법3 : 람다 표현식을 메소드 참조 표현식으로 기술		클래스명::메소드명
		List<MemberDto> list2 = memRepository
								.findAllByOrderByNumDesc()
								.stream()
								.map(MemberDto::toDto).toList();
		*/
		
		model.addAttribute("list", list);// 컨트롤러에 MemberDto가 담긴 list를 전달
	}
	
	@Override
	public void insert(MemberDto dto) {	//dto 타고 오는데 이거 entity로 바꿔줘야해!
		// JPA 작업 영역 내로 들어갈 때 일반 자료 전달용 (Dto, FormBean) 객체를 대응 Entity로 변환
		memRepository.save(Member.toEntity(dto));
	}
	
	// 수정 자료 읽기
	@Override
	public void getData(Long num, Model model) {
		Member m = memRepository.findById(num).get();// 수정할 멤버 엔티티를 가져온거야!
		
		model.addAttribute("dto", MemberDto.toDto(m)); // MemberDto로 만들어서 컨트롤러에게 주는거야
	}
	
	@Override
	public void update(MemberDto dto) {		// 1차 캐시를 사용하지 않는다.
		memRepository.save(Member.toEntity(dto));	// memberdto를 entity로 바꿔줘ㅓㅓ
		
	}
	
	@Transactional
	@Override
	public void update2(MemberDto dto) {
	    // 수정할 회원의 번호를 이용해서 회원 정보 entity 객체 얻어내기
	    Member m1 = memRepository.findById(dto.getNum()).get();
	    Member m2 = memRepository.findById(dto.getNum()).get();
	    
	    // 동일성 검사
	    boolean isEqual = m1 == m2;
	    System.out.println("m1과 m2가 같냐?" + isEqual);
	    
	    // setter 메소드를 이용해서 이름과 주소 수정하기
	    m1.setName(dto.getName());
	    m1.setAddr(dto.getAddr());
	}
	
	@Override
	public void delete(Long num) {
		memRepository.deleteById(num);
	}
}
