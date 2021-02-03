package spms.dao;

import java.util.List;

import spms.vo.LikeList;
import spms.vo.Member;
import spms.vo.Reservation;

public interface LikeListDao {

	public List<LikeList> selectList() throws Exception;
	public int insertLike(LikeList likeList) throws Exception;
	public int deleteLike(String shop_num) throws Exception;
	
}
