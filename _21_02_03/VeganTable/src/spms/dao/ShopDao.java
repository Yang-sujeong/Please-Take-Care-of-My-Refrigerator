package spms.dao;

import java.util.List;

import spms.vo.Member;
import spms.vo.Reservation;
import spms.vo.Shop;

public interface ShopDao {

	public List<Shop> selectList() throws Exception;
	public Shop selectOneShop(String no) throws Exception;

}
