package airline;

import java.sql.SQLException;
import java.util.Scanner;

public class View {
	Controller controller = new Controller();
	void menuLoop() throws SQLException {
		boolean isRun = true;
		while (isRun) {
			
			viewMenu(); // 메뉴 출력
			int choice = getUserSelect();// 선택값
			isRun = runChoice(choice); // 선택에 따른 동작
		}
	}
	
	void viewMenu() {
		System.out.println("======================MYREALTICKET===================");
		System.out.println("------------------------메뉴 선택-----------------------");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 항공스케쥴 조회");
		System.out.println("4. 예약 조회");
		System.out.println("5. 회원 정보 수정");
		System.out.println("6. 티켓 구매");
		System.out.println("7. 로그아웃");
		System.out.println("------------------------------------------------------");
	}
	int getUserSelect() {
		Scanner sc = new Scanner(System.in);
		System.out.print("번호를 선택하세요 >> ");
		return sc.nextInt();
	}
	boolean runChoice(int choice) throws SQLException {
		boolean isRun = true;
		
		switch (choice) {
		case 1:
			controller.join();
			break;
		case 2:
			controller.login();
			break;
		case 3:
			controller.searchSchedule();
			break;
		case 4:
			controller.rsvView();
			break;
		case 5:
			controller.setAccInfo();
			break;
		case 6:
			controller.buyTicket();
			break;
		case 7:
			controller.logout();
			isRun = false;
			break;
		default: // else에 해당하는 구문
			System.out.println("잘 못 입력하셨습니다...");
			break;
		}
		return isRun;
	}
}
