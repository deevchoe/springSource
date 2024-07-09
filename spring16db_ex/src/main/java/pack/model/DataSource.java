package pack.model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSource extends DriverManagerDataSource{	// init에서 써야될 DriverManagerDataSource를 class로 뽑아냈다. DB 연결 정보를 init에서 안쓰려고 바깥으로 뺐다.
	public DataSource() {
		setDriverClassName("org.mariadb.jdbc.Driver");
		setUrl("jdbc:mariadb://localhost:3306/test");
		setUsername("root");
		setPassword("9112");
	}
}
