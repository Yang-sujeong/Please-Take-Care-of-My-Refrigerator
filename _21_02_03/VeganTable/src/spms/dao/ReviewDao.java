package spms.dao;

import java.util.List;

import spms.vo.Member;
import spms.vo.Review;


public interface ReviewDao {
	public List<Review> selectList() throws Exception;
	
	public int insertRev(Review review) throws Exception;
	
	public List<Review> selectOneRev(String no) throws Exception;
}
