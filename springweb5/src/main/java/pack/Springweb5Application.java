package pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springweb5Application {

	public static void main(String[] args) {
		SpringApplication.run(Springweb5Application.class, args).getBean(Springweb5Application.class).execute();
	}

	@Autowired
	My my;
	
	@Autowired
	ProcessServiceImpl processServiceImpl;
	
	private void execute() {
		System.out.println("execute 메소드 수행");	// 웹서버를 수행시키면서도 어플리케이션을 수행시킬 수 있다
		my.aaa();
		processServiceImpl.selectProcess();
	}
}
