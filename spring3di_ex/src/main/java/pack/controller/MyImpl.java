package pack.controller;

import java.util.Scanner;
import pack.model.SangpumInter;

public class MyImpl implements MyInter {
    private SangpumInter inter;
    //private SangpumImpl inter; // 클래스 주지마. 위처럼 인터페이스를 줘야해 그래야 SangpumInter의 모든걸 갖다 쓸 수 있어. SangpumImpl로 하면 SangpumImpl만 갖다 쓸 수 있어!!! 뭘 쓸지는 생성자를 통해 들어와 init.xml이 그걸 정해줘
    private String sang;
    private int money;

    // 생성자
    public MyImpl(SangpumInter inter) {	// init.xml 
        this.inter = inter;
    }

    @Override
    public void inputData() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("상품명 : ");
            sang = scanner.next(); // 필드와 지역 변수 혼동 방지
            System.out.print("수량 : ");
            int cnt = scanner.nextInt();
            System.out.print("단가 : ");
            int dan = scanner.nextInt();
            
            money = inter.calcMoney(cnt, dan);
        } catch (Exception e) {
            e.printStackTrace(); // 예외를 출력하여 디버깅
        }
    }

    @Override
    public void showData() {
        StringBuffer sb = new StringBuffer();
        sb.append("상품명 : ").append(sang).append(" 금액 : ").append(money);
        System.out.println(sb.toString()); // 결과를 출력
    }
}