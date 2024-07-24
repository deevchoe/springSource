package pack.controller;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {
	private String name;
	private int price;
	private String data;
}
