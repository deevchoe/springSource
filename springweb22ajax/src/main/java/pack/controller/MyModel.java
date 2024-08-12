package pack.controller;
// vo 클래스야. 여기에 담아서 전달하면... pojo.... 

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MyModel {
	private String name;
	private String skills[];
}
