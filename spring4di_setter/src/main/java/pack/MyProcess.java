package pack;

public class MyProcess {	// setter injection 사용
	private int nai;
	private String name;
	private ShowData showData;	// 참조형.       포함관계
	
	public MyProcess() {
		
	}
	
	public void setNai(int nai) {
		this.nai = nai;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setShowData(ShowData showData) {	// 이름만 있지 실체가 없기 때문에 값을 안주면 null 에러가 떨어진다
		this.showData = showData;
	}
	
	public void displayData() {
		System.out.println("이름은 " + name + ", 나이는" + nai + 
				", 별명은 " + showData.processNickName() +
				", 취미는 " + showData.processHobby());
	}
}
