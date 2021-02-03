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
import spms.vo.Review;
import spms.vo.Shop;

@Component("shopDao")
public class MySqlShopDao implements ShopDao{

	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<Shop> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		final String sqlSelect = "SELECT shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, shop_map FROM shop";

		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelect);

			ArrayList<Shop> shops = new ArrayList<Shop>();

			while (rs.next()) {
						shops.add(new Shop()
								.setShop_num(rs.getInt("shop_num"))
								.setShop_name(rs.getString("shop_name"))
								.setShop_address(rs.getString("shop_address"))
								.setShop_tel(rs.getString("shop_tel"))
								.setShop_type(rs.getString("shop_type"))
								.setShop_time(rs.getString("shop_time"))
								.setShop_hp(rs.getString("shop_hp"))
								.setShop_map(rs.getString("shop_map")));
			}

			return shops;

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

	public Shop selectOneShop(String shop_num) throws Exception {
		Connection connection = null;
		Shop shop = null;
		Statement stmt = null;
		ResultSet rs = null;

//		final String sqlSelectOne = "SELECT * FROM shop LEFT JOIN review USING (shop_num) WHERE shop_num=";
		
//		final String sqlSelectOne = "SELECT s.shop_num, shop_name, shop_address, shop_tel, shop_type, shop_time, shop_hp, "
//							+ "IFNULL(id, '값없음') id, IFNULL(text, '값없음') text FROM shop AS s "
//							+ "LEFT JOIN review AS r ON s.shop_num= r.shop_num WHERE s.shop_num=";
		final String sqlSelectOne = "SELECT * FROM shop WHERE shop_num=";
		try {
			// 커넥션풀에서 Connection객체를 빌려온다
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sqlSelectOne + shop_num);
//			ArrayList<Shop> shops = new ArrayList<Shop>();
			if (rs.next()) {	
					shop = new Shop()
							.setShop_num(rs.getInt("shop_num"))
							.setShop_name(rs.getString("shop_name"))
							.setShop_address(rs.getString("shop_address"))
							.setShop_tel(rs.getString("shop_tel"))
							.setShop_type(rs.getString("shop_type"))
							.setShop_time(rs.getString("shop_time"))
							.setShop_hp(rs.getString("shop_hp"))	
							.setShop_map(rs.getString("shop_map"));	
			 
			} else {
				throw new Exception("shopDao 셀렉원 에러");
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
			return shop;
	} 	
	
}
