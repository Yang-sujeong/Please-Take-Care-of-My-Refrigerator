package airline;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Controller {
	private static Scanner sc = new Scanner(System.in);
	private static Connection con;
	private static CurAccount ca = null;
	private String sql;
	static {
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "bitairline";
			String pw = "bitairline";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void join() throws SQLException {
		if (ca == null) {
			System.out.println("========================회원가입=====================");
			System.out.print("ID :\t");
			String id = sc.nextLine();
			System.out.print("PW :\t");
			String pw = sc.nextLine();
			System.out.print("이름 :\t");
			String name = sc.nextLine();
			System.out.print("셩벌 :\t");
			String sex = sc.nextLine();
			System.out.print("전화번호 : ");
			String phone = sc.nextLine();
			System.out.print("주민등록번호 : ");
			String rrn = sc.nextLine();

			// 하나의 회원
			sql = "INSERT INTO passenger VALUES (ACC_NUM_SQ.NEXTVAL,'" + name + "','" + sex + "','" + rrn + "','"
					+ phone + "')";
			try {
				Statement stmt = con.createStatement();
				stmt.executeUpdate(sql);
				sql = "INSERT INTO acc VALUES (ACC_NUM_SQ.CURRVAL,'" + id + "','" + pw + "')";
				stmt.executeUpdate(sql);
				System.out.println("-------------------------------------------");
				sql = "SELECT ACC_NUM FROM acc WHERE id = '" + id + "' AND pw = '" + pw + "' ";
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String acc_num = rs.getString(1);
					System.out.println("회원번호 : " + acc_num);
				}
				System.out.println(name + "님의 회원가입이 완료되었습니다.\n");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("로그아웃 후에 회원가입이 가능합니다.");
		}
	}

		public void login() throws SQLException {
		// 검사
		if (ca == null) {
			System.out.println("========================로그인=====================");
			System.out.println("ID : ");
			String userid = sc.nextLine();
			System.out.println("PW : ");
			String userpw = sc.nextLine();

			String strSelect = "SELECT * FROM acc ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(strSelect);

			String passLoginName = null;
			String passAccnum = null;

			while (rs.next()) {
				if (rs.getString(2).equals(userid) && rs.getString(3).equals(userpw)) {
					passAccnum = rs.getString(1);
					String selectname = "SELECT name FROM passenger WHERE acc_num = " + "'" + passAccnum + "' ";

					Statement Stmt = con.createStatement();
					ResultSet rs1 = Stmt.executeQuery(selectname);
					while (rs1.next()) {
						passLoginName = rs1.getString(1);
					}
				}
			}
			// 출력
			if (passLoginName != null) {
				System.out.println(passLoginName + "님 환영합니다!\n");
				ca = new CurAccount(passAccnum, userid, passLoginName);// 로그인 정보 등록
			} else
				System.out.println("회원정보가 없습니다");
			stmt.close();
		} else {
			System.out.println("이미 로그인 상태입니다.");
		}
	}


	public void searchSchedule() throws SQLException {
		if (ca == null) {
			System.out.println("로그인 후에 이용가능합니다.");
		} else {
			new Table(ca); // 현재 계정 정보를 받기 위한 파라미터 입력
		}
	}

	public void rsvView() throws SQLException {
		if (ca != null) {
			sql = "SELECT reservation_num,acc_num,schedule_num,TO_CHAR(reservation_date,'YYYY/MM/DD')"
					+ ", seat_num, TO_CHAR(price,'999,999,999')||'원' "
					+ "FROM RESERVATION WHERE acc_num='" + ca.getAccNum()+"'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			System.out.println();
			System.out.println("======================예약 조회====================");
			System.out.println("-------------------------------------------------\n"
					+ "|예약번호\t|회원번호\t|스케쥴번호\t|예약일\t\t|좌석번호\t|가격\t\t|");
			String result = "|";
			while (rs.next()) {
				for (int i = 1; i <= 6; i++) {
					if (i <= 2) {
						result += rs.getInt(i) + "\t|";
					} else {
						result += rs.getString(i) + "\t|";
					}
				}
				result += "\n|";
			}
			System.out.println(result);
		} else {
			System.out.println("로그인 후 이용가능합니다.");
		}
	}

	public void setAccInfo() throws SQLException {
		if (ca != null) {
			String str = "UPDATE acc SET id=? , pw=? WHERE id = '" + ca.getId() + "'";
			// SQL 문으로 변형
			PreparedStatement pstmt = con.prepareStatement(str);
			
			System.out.println("========================회원 정보 수정=====================");
			System.out.println("변경 ID");
			String result = sc.next();
			pstmt.setString(1, result);
			ca.setId(result);
			System.out.println("변경 PW");
			result = sc.next();
			pstmt.setString(2, result);
			// 쿼리문 실행
			pstmt.executeUpdate();

			System.out.println("ID , PW 가 변경되었습니다.\n");
		} else {
			System.out.println("로그인 상태가 아닙니다.");
		}
	}

	public void buyTicket() throws SQLException {
		int price;
		int rowCount;
		int columnCount;
		Date date;
		String ans;
		String text;
		String today;
		String grade;
		String format;
		String airline;
		String selectedSch;
		String scheduleNum = null;
		DecimalFormat priceFormat;
		SimpleDateFormat sdf;
		Statement stmt=con.createStatement();//공용 Statement DB연결
		Statement stmtCart = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//카트 접근 전용 Statement DB연결
		ResultSet rsCart;
		ResultSet rsView;
		if (ca != null) {
			sql = "SELECT * FROM cart WHERE acc_num=" + ca.getAccNum();
			
			rsCart = stmtCart.executeQuery(sql);//
			rsCart.last();//마지막 행으로 커서 이동
			rowCount = rsCart.getRow(); //마지막 행 번호 추출
			rsCart.beforeFirst();//첫번째 행으로 커서 이동
			
			columnCount = 8;// 열 개수 입력
			System.out.println("========================티켓 구매=====================");
			System.out.println("고객님의 카트에 " + rowCount + "개의 스케쥴이 담겨있습니다."); // 몇 번의 과정을 반복할 지 안내함
			for (int j = 0; j < rowCount; j++) {
				rsCart.next();
				scheduleNum = rsCart.getString(3);// 스케쥴 번호 추출
				
				selectedSch = "|"; //아래 컬럼명 출력문과 동일하게 행의 내용 포맷을 작성 중
				for (int i = 1; i <= columnCount; i++) {
					selectedSch += rsCart.getString(i)+"\t|";
				}//작성 완료
				
				System.out.println(rowCount + "개 중 " + (j + 1) + "번째 티켓입니다.\n"//전체 스케쥴 중 구매진행상황 보고
						+ "---------------------------------------------------------------------------------- \n"
						+ "|카트번호\t|회원번호\t|스케쥴번호\t|편명\t|출발지\t|도착지\t|출발일시\t\t\t|도착일시\t\t\t| \n" 
						+ selectedSch + "\n"
						+ "----------------------------------------------------------------------------------"); 
				text="해당 항공권으로 결제하시겠습니까?(y/n)";
				format = "(?i)y|n";//정규표현식
				ans = check(format,text);//유효성 검사 후 응답받음
				if (ans.equals("y") || ans.equals("Y")) {// 결제 정보 입력
					text = "구매하고자 하는 좌석의 등급을 입력하십시오.(이코노미,비즈니스,퍼스트)";
					format = "^이코노미|비즈니스|퍼스트$";//정규표현식
					grade = check(format, text);//유효성 검사 후 응답받음
					sql = "SELECT * FROM rsv_view WHERE 스케쥴번호='" + scheduleNum + "' AND 등급='" + grade + "'";// 스케쥴 번호와 등급을 이용해서 가격정보 추출
					rsView = stmt.executeQuery(sql);
					rsView.next();//추출한 행으로 커서를 이동
					
					if (grade.equals("이코노미")) {// 시퀀스 명령문 작성시 넣을 등급별 문자열 변환
						grade = "ECO";
					} else if (grade.equals("비즈니스")) {
						grade = "BSN";
					} else {// 퍼스트
						grade = "FST";
					}
					price = rsView.getInt(3);// 가격 추출
					priceFormat = new DecimalFormat("#,###");// 금액의 포맷 지정
					
					airline = rsView.getString(4); // 항공사 추출 (시퀀스 명에 넣기 위한 작업)
					text = "해당 티켓의 가격은 " + priceFormat.format(price) + "원 입니다.\n" //가격 안내
					     + "구매하시겠습니까?(y/n)";//구매 여부를 묻는다
					format = "(?i)y|n";//정규표현식
					ans = check(format, text);//유효성 검사 후 응답을 받음
					if (ans.equals("y") || ans.equals("Y")) {//구매할 경우
						
						//DB의 DATE 타입의 자료형으로 오늘날짜를 입력하기 위한 작업
						sdf = new SimpleDateFormat("yyyy-MM-dd");// 문자열로 받을 날짜의 포맷지정
						today = sdf.format(new java.util.Date());// 오늘 날짜를 String 형식으로 받아온다.
						date = Date.valueOf(today);// String 형식의 오늘날짜를 SQL전용 Date객체로 변환한다.

						sql = "INSERT INTO reservation VALUES "//reservation 테이블 입력 SQL문 작성
								+ "(RSV_NUM_SQ.NEXTVAL,'" + ca.getAccNum() + "','"//예약번호 시퀀스,회원번호
								+ scheduleNum + "','" + date + "'," //스케쥴번호,예약날짜
								+ "'"+grade+"'||"+airline+"_"+grade+"_SEAT_SQ.NEXTVAL,"//시퀀스 입력 (등급||시퀀스번호)
								+ "'" + price + "')";//좌석금액
						stmt.executeUpdate(sql);//SQL문 실행
						System.out.println("구매가 완료되었습니다.");
						
						sql = "DELETE FROM cart WHERE cart_num=" + rsCart.getInt(1);//구매한 항목의 행을 카트에서 삭제하는 SQL문 작성
						//cart_num은 시퀀스로 입력한 고유의 pk이기 때문에 이 부분만 맞춰주면 원하는 행을 지울 수 있다.
						stmt.executeUpdate(sql);//SQL문 실행 -> 구매한 스케쥴 정보 삭제
					}
				} else {//구매하지 않았을 경우
					text = "구매하지 않은 항공권은 자동으로 카트에서 삭제됩니다.\n" 
				         + "정말 구매하지 않으시겠습니까?(y/n)";
					format = "(?i)y|n";//정규표현식
					ans = check(format, text);//유효성 검사 후 응답 받음
					if (ans.equals("y") || ans.equals("Y")) {//구매를 하지 않을 경우
						sql = "DELETE FROM cart WHERE cart_num=" + rsCart.getInt(1);//해당 행의 카트넘버를 입력
						stmt.executeUpdate(sql);//삭제실행
						System.out.println("해당 항공권이 카트에서 삭제되었습니다.");
						
						text = "구매를 종료할 경우 남은 항공권은 전부 카트에서 삭제됩니다. \n"
						     + "구매를 계속 진행하시겠습니까?(y/n)";
						format = "(?i)y|n";//정규표현식
						ans = check(format, text);//유효성 검사 후 응답 받음
						
						if (ans.equals("y") || ans.equals("Y")) {//구매를 계속 진행할 경우
							if(j==rowCount-1) {//더 이상 구매할 항목이 남아있지 않을 경우
								System.out.println("카트에 담긴 항공권이 없습니다. 메인으로 돌아갑니다.");
								break;//돌아가도 자동으로 반복문이 완료되지만, 이해를 돕기 위해서 명시한 부분
							} else {//구매할 항목이 남아있을 경우
								System.out.println("구매를 계속 진행합니다.");
								continue;//메인 반복문의 처음으로 돌아감
							}
							
							
						} else {//구매를 종료할 경우
							sql="DELETE FROM cart WHERE cart_num IN (";//행 전체를 삭제하기 위한 'IN' 사용
							
							for (j+=1; j < rowCount; j++) {//위에서 하나의 행을 '삭제'했으므로 j는 하나 증가해야한다. 
								                           //j는 삭제 횟수와 같기 때문이다.
														   //티켓을 구매했을 경우 -> 행을 삭제 후 반복문의 처음으로 돌아감 -> j++
								                           //티켓을 구매하지않고 해당 행 삭제 후 구매를 계속 진행할 경우-> j++ (행을 삭제했으므로 ++)
														   //티켓을 구매하지 않음 -> 삭제 되는데 괜찮아? -> 아닝,,그냥 구매할래..-> j 변동 없음 (삭제하지 않았으므로)
														   //결론 : 이 반복문은 구매를 하건 안하건 결과적으로 들어온 순간 카트에 담긴 모든 스케쥴을 소진하도록 설계되어있다.
								
								rsCart.next();//위에서 삭제를 실행했으므로 커서를 아래로 내린다.
								
								sql+=rsCart.getInt(1); //WHERE cart_num IN (1,2,3,4,5) 의 포맷 작성
								if(j==rowCount-1) {//마지막이라면,
									sql+=")";//괄호를 닫아준다.
								}else {//마지막이 아니라면
									sql+=",";//쉼표를 붙여서 계속 이어준다.
								}
							}//SQL문 작성 반복문 end
							stmt.executeUpdate(sql);//완성된 SQL문 예시 DELETE FROM cart where cart_num IN (4,5,6,7)
							System.out.println("저장하신 모든 항공권이 카트에서 삭제되었습니다.\n" 
							                 + "메인메뉴로 돌아갑니다.");
							
							break;//사실 break를 걸지 않아도 반복문은 끝난다. 
							      //J가 rowCount에 도달하기때문
							      //이 부분은 지워도 상관없지만, 명백히 알려주기 위함
						}
						
					} else {//구매를 진행하지 않는다. -> 티켓이 삭제되는데 괜찮은가? -> "아뇨, 그냥 구매할래요." 부분
						System.out.println("구매를 다시 진행합니다.");
						//티켓을 삭제하지 않으므로 j값이 ++되면 안된다. 또한, 커서도 내려가면 안된다.
						j--;// continue시에 j 값이 +1 되므로 미리 깎아준다. 
						rsCart.previous();// continue시에 커서가 내려가므로 미리 한번 올려준다. next()의 반대
						continue;
					}
				}
			} // 메인for문 end
		} else {//ca==null의 경우
			System.out.println("비회원 구매는 지원하지 않습니다.");
		}
	}
	public void logout() {

		ca = null;
		System.out.println("이용해주셔서 감사합니다.");
		return;
	}

	public String[][] getTableData() {
		sql = "SELECT s.flight_name,f.airline_name,departure,arrival,TO_CHAR(departure_datetime,'YYYY/MM/DD'),TO_CHAR(departure_datetime,'HH24:MI'),"
				+ "TO_CHAR(arrival_datetime,'YYYY/MM/DD'),TO_CHAR(arrival_datetime,'HH24:MI') "
				+ "FROM schedule s, flight f " + "WHERE s.flight_name=f.flight_name";
		String[][] data = null;
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(sql);
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			data = new String[rowCount][8];
			for (int i = 0; i < rowCount; i++) {
				rs.next();
				for (int j = 0; j < 8; j++) {
					data[i][j] = rs.getString(j + 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return data;
		}
	}

	public String[][] searchTableData(String[] comboBox) {// comboBox[0=출발지,1=도착지,2=출발일자:시간 3,항공사]
		if (!comboBox[0].equals("")) {// 공백이 아니라면
			comboBox[0] = "AND departure='" + comboBox[0] + "' ";// 마지막 공백은 sql조건문 간의 공백을 의미함
		}
		if (!comboBox[1].equals("")) {// 공백이 아니라면
			comboBox[1] = "AND arrival='" + comboBox[1] + "' ";
		}
		if (!comboBox[3].equals("")) {
			comboBox[3] = "AND airline_name='" + comboBox[3] + "' ";
		}
		sql = "SELECT s.flight_name,f.airline_name,departure,arrival,TO_CHAR(departure_datetime,'YYYY/MM/DD'),TO_CHAR(departure_datetime,'HH24:MI'),"
			+ "TO_CHAR(arrival_datetime,'YYYY/MM/DD'),TO_CHAR(arrival_datetime,'HH24:MI') "
				
			+ "FROM schedule s, flight f " 
			+ "WHERE s.flight_name = f.flight_name "
			+ "AND TO_CHAR(departure_datetime,'YYYY/MM/DD:HH24')>='" + comboBox[2] + "' " 
			+ comboBox[0]
			+ comboBox[1] 
		    + comboBox[3]; // 공백일 경우 영향을 줄 수 없도록 순서 배치

		String[][] data = null;
		try {
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(sql);
			rs.last();// 바로 마지막 행으로
			int rowCount = rs.getRow();
			rs.beforeFirst();
			data = new String[rowCount][8];
			for (int i = 0; i < rowCount; i++) {
				rs.next();
				for (int j = 0; j < 8; j++) {
					data[i][j] = rs.getString(j + 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return data;
		}
	}

	public void insertCart(String[] cart) {
		sql = "INSERT INTO cart ("
				+ "SELECT cart_num_sq.NEXTVAL, acc_num, schedule_num, flight_name,departure,arrival,departure_datetime,arrival_datetime "
				+ "FROM acc, schedule " + "WHERE acc_num=" + ca.getAccNum() + " " + "AND flight_name=? "// 편명
				+ "AND departure=? "// 출발지
				+ "AND arrival=? "// 도착지
				+ "AND TO_CHAR(departure_datetime,'YYYY/MM/DD:HH24:MI')=? "// 출발일자:시간
				+ "AND TO_CHAR(arrival_datetime,'YYYY/MM/DD:HH24:MI')=? )";// 도착시간:시간
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			for (int i = 0; i < cart.length; i++) {
				pstmt.setString(i + 1, cart[i]);// 첫번째 물음표에 cart[0]의 값이 들어감
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String check(String format, String text) {
		String temp;
		while (true) {
			System.out.println(text);
			temp = sc.nextLine();
			if (Pattern.matches(format, temp)) {// 유효성 검사 (true/false) 반환
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
		return temp;
	}
}
