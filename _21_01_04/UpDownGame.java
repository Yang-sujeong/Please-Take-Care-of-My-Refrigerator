package til._21_01_04;

import java.util.Scanner;

public class UpDownGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int random = (int) (Math.random()*101);
		int count1 = 1;
		boolean bo = true;
		System.out.println("-----------업다운 게임--------------");
		System.out.println("computer의 숫자를 맞추는 게임입니다  ");
		System.out.println("---------------------------------");
		while(bo) {
			System.out.println("0~100 사이의 숫자를 입력하세요");
			System.out.print(">>");
			int userNum = sc.nextInt();
			if(userNum >= 0 && userNum <= 100) {
				if (random > userNum) {System.out.println("user의 수가 작습니다");}
				if (random < userNum) {System.out.println("user의 수가 큽니다");}
				if (random == userNum) {
					System.out.println("맞췄습니다");
					bo = false;
					System.out.println("----------------------------------");
					System.out.println("결과 : " + count1 + "회만에 맞췄습니다");
				}
			count1++;
			} else {
				continue;
			}
		}

	}

}
