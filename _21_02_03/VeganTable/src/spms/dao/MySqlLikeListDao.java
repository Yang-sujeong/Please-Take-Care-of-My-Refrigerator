package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.LikeList;
import spms.vo.Member;
import spms.vo.Reservation;

@Component("LikeListDao")
public class MySqlLikeListDao implements LikeListDao{

	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<LikeList> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		final String sqlSelect = "SELECT distinct id, l.shop_num, shop_name "
				+ "FROM likelist AS l JOIN shop AS s ON l.shop_num = s.shop_num";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);

			ArrayList<LikeList> likeList = new ArrayList<LikeList>();

			while (rs.next()) {
				likeList.add(new LikeList()
								.setId(rs.getString("id"))
								.setShop_num(rs.getInt("l.shop_num"))
								.setShop_name(rs.getString("shop_name")));
			}

			return likeList;

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

			try {
				if(connection != null)
					connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int insertLike(LikeList likeList) throws Exception {
		Connection connection = null;
		int result = 0;
		PreparedStatement stmt = null;
		final String sqlInsert = "INSERT INTO likelist(id, shop_num) VALUES(?, ?)";

		try {
			connection = ds.getConnection();

			stmt = connection.prepareStatement(sqlInsert);
			stmt.setString(1, likeList.getId());
			stmt.setInt(2, likeList.getShop_num());
			
			
			
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

	public int deleteLike(String shop_num) throws Exception {
		Connection connection = null;
		int result = 0;
		Statement stmt = null;
		final String sqlDelete = "DELETE FROM likelist WHERE shop_num=";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			result = stmt.executeUpdate(sqlDelete+shop_num);

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
	
	
}
