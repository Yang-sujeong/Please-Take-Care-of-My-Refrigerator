package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.ReservationDao;
import spms.vo.Member;
import spms.vo.Reservation;

@Component("/reservation/add.do")		// 예약하기
public class ReservationAddController implements Controller, DataBinding {
	
	ReservationDao reservationDao = null;
	
	public ReservationAddController setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		// key값 이름, 자동으로 생성해야 할 클래스 타입
		return new Object[] {
			"reservation", spms.vo.Reservation.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Reservation reservation = (Reservation)model.get("reservation");
		if(reservation.getId() == null || reservation.getId() == "") {
			return "redirect:../auth/login.do";
		}else {
			reservationDao.insertRsv(reservation);			
			return "redirect:list.do";
		}
	}


}
