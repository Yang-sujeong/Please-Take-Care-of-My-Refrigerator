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
import spms.vo.Review;

@Component("reviewDao")
public class MySqlReviewDao implements ReviewDao {

	DataSource ds = null;

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Review> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sqlSelect = 
				"SELECT rev_num , id, shop_num, text FROM review";
		//내가 입력한 리뷰만 보이는		
		//"SELECT * FROM review JOIN member USING(id) WHERE id= 'ny'" ;

		try {

			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);
			
			ArrayList<Review> reviews = new ArrayList<Review>();
			
			while(rs.next()) {
				reviews.add(new Review()
						.setRev_num(rs.getInt("rev_num"))
						.setText(rs.getString("text"))
						.setId(rs.getString("id"))
						.setShop_num(rs.getInt("shop_num")));
			}
			return reviews;
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
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public int insertRev(Review review)  throws Exception {
		Connection connection = null;
		int result = 0;
		PreparedStatement stmt = null;
		final String sqlInsert = "INSERT INTO review (id,shop_num,text, rating) VALUES(?, ?, ?, ?)";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.prepareStatement(sqlInsert);
			stmt.setString(1, review.getId());
			stmt.setInt(2, review.getShop_num());
			stmt.setString(3, review.getText());
			stmt.setString(4, review.getRating());
			
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
	public List<Review> selectOneRev(String no) throws Exception {
		Connection connection = null;
		Review review = null;
		Statement stmt = null;
		ResultSet rs = null;

		final String sqlSelectOne = "SELECT rev_num, text, id, shop_num, rating FROM review WHERE shop_num='"+no+"' ORDER BY rev_num DESC";
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelectOne);
			while (rs.next()) {
				reviews.add(new Review()
						.setRev_num(rs.getInt("rev_num"))
						.setText(rs.getString("text"))
						.setId(rs.getString("id"))
						.setShop_num(rs.getInt("shop_num"))
						.setRating(rs.getString("rating")));
						
			} 
			return reviews;

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

	}
	
	
}
