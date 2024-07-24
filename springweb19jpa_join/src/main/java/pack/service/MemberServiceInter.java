package pack.service;

import org.springframework.ui.Model;

import pack.dto.MemberDto;

public interface MemberServiceInter {
	public void getList(Model model);	// 회원 자료 전체 조회
	public void insert(MemberDto dto);	// 원래라면 formbean 만들어서 그걸로 해줘야해ㅐ
	public void getData(Long num, Model model);
	public void update(MemberDto dto);
	public void update2(MemberDto dto);
	public void delete(Long num);
}
