package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.ReservationDao;

@Component("/reservation/delete.do")		// 예약삭제하기
public class ReservationDeleteController implements Controller, DataBinding {
	
	ReservationDao reservationDao = null;
	
	public ReservationDeleteController setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		String rsv_num = (String)model.get("rsv_num");
		reservationDao.deleteRsv(rsv_num);
		
		return "redirect:list.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"rsv_num", String.class
		};
	}

}








