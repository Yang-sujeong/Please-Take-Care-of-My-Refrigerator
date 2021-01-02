package til._21_01_02;

import java.util.Arrays;
import java.util.Scanner;

public class BallGame {
	Scanner sc = new Scanner(System.in);
	private int[] ran = new int[3];
	private int[] user = new int[3];
	boolean isRun = true;
	int selMenu;
	
	public void generatorThreeNum () {
		boolean boo = true;
		while (boo) {
			int ran1 = (int) (Math.random()*10);
			int ran2= (int) (Math.random()*10);
			int ran3 = (int) (Math.random()*10);
				if (ran1 == ran2 || ran2 == ran3 || ran1 == ran3) {
					continue;
				}	else { ran[0] = ran1; ran[1] = ran2; ran[2] = ran3;
							boo = false;
					}	
		}
		
	}
	public void baseBallMenu () {
		System.out.println("============= 삼진아웃게임 =============");
		System.out.println("------------ 게임방법 ------------");
		System.out.println("① 0~9까지 3개의 숫자중 중복없이 임의의 숫자를 선택");
		System.out.println("② 같은 숫자 동일한 자리일 경우 1스트라이크");
		System.out.println("③ 같은 숫자 다른 자리일 경우 1볼");
		System.out.println("④ 세자리 숫자 모두 자리까지 일치할 경우는 삼진아웃!!");
		System.out.println("--------------------------------");
		System.out.println("게임 시작 >>  1");
		System.out.println("게임 종료 >>  0");
		System.out.println("--------------------------------");
	}
	
	public void userNum() {
		System.out.println("첫번째 수를 입력해주세요");
		System.out.print(">>");
		int user1 = sc.nextInt();
		System.out.println("두번째 수를 입력해주세요");
		System.out.print(">>");
		int user2 = sc.nextInt();
		System.out.println("세번째 수를 입력해주세요");
		System.out.print(">>");
		int user3 = sc.nextInt();
		
		user[0] = user1;
		user[1] = user2;
		user[2] = user3;
		
	}
	void decisionBall() {
//		System.out.println(Arrays.toString(ran)); 
		int strike = 0;
		int ball = 0;
		boolean boo = true;
		while(boo) {
		userNum();
			for (int i = 0; i < ran.length; i++) {
				if(user[0] == ran[i] && user[0] != ran[0]) {ball++;}
				if(user[1] == ran[i] && user[1] != ran[1]) {ball++;}
				if(user[2] == ran[i] && user[2] != ran[2]) {ball++;}
			}
			if(user[0] == ran[0]) {strike++;}
			if(user[1] == ran[1]) {strike++;}
			if(user[2] == ran[2]) {strike++;}
			if(user[0] == ran[0] && user[1] == ran[1] && user[2] == ran[2]) {
				boo = false;
			}
		
		if (strike == 3 && ball == 0) {
			System.out.print("===> ");
			System.out.print("삼진아웃!!");
		}else {
			System.out.print("===> ");
			System.out.println(strike + " 스트라이크 " + ball + " 볼");
		}
		System.out.println();
		strike = 0;
		ball = 0;		

		}
	}
	void reGame() {
		System.out.println("--------------------------------");
		System.out.println("다시하시겠습니까?(Y/N)");
		System.out.print(">>");
		String re = sc.next();
		 if (re.equals("y")) {isRun = true;}
		 else {
			 System.out.println("--------------------------------");
			 System.out.println("게임이 종료됩니다");
			 isRun = false;
			 }
	}
	void mainRun() {
		
		
		while(isRun) {
			baseBallMenu();
			int selMenu = sc.nextInt();
		switch(selMenu) {
		case 1:
			generatorThreeNum();
			decisionBall();
			reGame();
			break;
		case 0:
			isRun = false;
			System.out.println("게임이 종료됩니다");
			break;
		default:
			System.out.println("잘못입력하셨습니다");
			break;
		}
	}
	}
	
	
	
}		// end
