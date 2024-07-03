package controller;

import model.DataDao;

public class ProcessServiceImpl implements ProcessService{
	private DataDao dataDao;	// 클래스의 포함관계

//	public ProcessServiceImpl() { // 스프링에서는 아규먼트 없는 생성자도 써주는 것을 권장한다.
//		// TODO Auto-generated constructor stub
//	}

	public ProcessServiceImpl(DataDao dataDao) {
		this.dataDao = dataDao;
	}
	
	@Override
	public void selectProcess() {
		System.out.println("selectProcess 처리 시작");
		dataDao.selectData(); 	// model 영역의 클래스가 수행
		System.out.println("selectProcess 처리 끝");
	}
}
