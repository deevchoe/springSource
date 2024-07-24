package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pack.entity.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer>{
	//int <- 0	// 기본형   값을 안주면 초기치 0
	//Integer <- Null	// 참조형    값을 안주면 초기치 Null
}
