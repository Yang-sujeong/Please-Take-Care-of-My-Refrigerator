package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.MemberDao;
import spms.dao.ReservationDao;
import spms.vo.LikeList;
import spms.vo.Member;
import spms.vo.Reservation;

@Component("/reservation/list.do")		// 예약목록 확인하기
public class ReservationListController implements Controller {

	ReservationDao reservationDao;
	
	public ReservationListController setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
	
			model.put("reservation", reservationDao.selectList());
			return "/detailPage/ReservationList.jsp";
		
	}

}
