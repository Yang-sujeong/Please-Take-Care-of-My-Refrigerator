package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Member;
import spms.vo.Reservation;

@Component("reservationDao")
public class MySqlReservationDao implements ReservationDao{

	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<Reservation> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		final String sqlSelect = "SELECT rsv_num, id, r.shop_num, shop_name, rsv_date, rsv_totalnum "
							+ "FROM reservation AS r JOIN shop AS s "
							+ "ON r.shop_num = s.shop_num ORDER BY rsv_date DESC";
		
		

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);

			ArrayList<Reservation> reservations = new ArrayList<Reservation>();

			while (rs.next()) {
				reservations.add(new Reservation()
								.setRsv_num(rs.getInt("rsv_num"))
								.setId(rs.getString("id"))
								.setShop_num(rs.getInt("r.shop_num"))
								.setShop_name(rs.getString("shop_name"))
								.setRsv_date(rs.getString("rsv_date"))
								.setRsv_totalnum(rs.getInt("rsv_totalnum")));
			}

			return reservations;

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			/* ds에서 제공하는 Connection객체의 close()의 의미는
			 * 연결을 종료하는 것이 아니라
			 * 객체를 ds내부의 커넥션 풀에 반납한다는 의미이다
			 * */
			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int insertRsv(Reservation reservation) throws Exception {
		Connection connection = null;
		int result = 0;
		PreparedStatement stmt = null;
		final String sqlInsert = "INSERT INTO reservation(rsv_num, id, shop_num, rsv_date, rsv_totalnum) VALUES(?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();

			stmt = connection.prepareStatement(sqlInsert);
			stmt.setInt(1, reservation.getRsv_num());
			stmt.setString(2, reservation.getId());
			stmt.setInt(3, reservation.getShop_num());
			stmt.setString(4, reservation.getRsv_date());
			stmt.setInt(5, reservation.getRsv_totalnum());
			
			result = stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteRsv(String rsv_num) throws Exception {
		Connection connection = null;
		int result = 0;
		Statement stmt = null;
		final String sqlDelete = "DELETE FROM reservation WHERE rsv_num=";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			result = stmt.executeUpdate(sqlDelete + rsv_num);

		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 다 썼으면 반납하자
			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	

	public Reservation selectOneRsv(String id) throws Exception {
		Connection connection = null;
		Reservation reservation = null;
		Statement stmt = null;
		ResultSet rs = null;

		final String sqlSelectOne = "SELECT rsv_num, id, shop_num, rsv_date, rsv_totalnum FROM reservation WHERE id="+id+"ORDER BY rsv_date DESC";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelectOne);
			if (rs.next()) {
				reservation = new Reservation()
							.setRsv_num(rs.getInt("rsv_num"))
							.setId(rs.getString("id"))
							.setShop_num(rs.getInt("shop_num"))
							.setRsv_date(rs.getString("rsv_date"))
							.setRsv_totalnum(rs.getInt("rsv_totalnum"));
						
			} else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}

			// 다 썼으면 반납하자
			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return reservation;
	} 
	
	
}
