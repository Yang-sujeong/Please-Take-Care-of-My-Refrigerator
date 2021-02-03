package spms.dao;

import java.util.List;

import spms.vo.Member;
import spms.vo.Reservation;

public interface ReservationDao {

	public List<Reservation> selectList() throws Exception;
	public int insertRsv(Reservation reservation) throws Exception;
	
	public int deleteRsv(String id) throws Exception;
		
	public Reservation selectOneRsv(String rsv_num) throws Exception;

}
