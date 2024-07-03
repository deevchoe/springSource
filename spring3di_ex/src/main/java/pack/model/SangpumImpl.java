package pack.model;

public class SangpumImpl implements SangpumInter {

	@Override
	public int calcMoney(int cnt, int dan) {
		int m = cnt * dan;
		
		return m;
	}


}
