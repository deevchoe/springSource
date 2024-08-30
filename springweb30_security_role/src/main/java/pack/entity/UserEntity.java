package pack.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {	// db가 있다고 가정..
	private int id;
	private String userName;
	private String userPass;
	private String email;
	private String role;	// Authority 정보를 저장 할 칼람. ROLE_XXX
}
