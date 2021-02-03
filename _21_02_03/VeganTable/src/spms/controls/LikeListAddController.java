package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.LikeListDao;
import spms.dao.MemberDao;
import spms.dao.ReservationDao;
import spms.vo.LikeList;
import spms.vo.Member;
import spms.vo.Reservation;

@Component("/likeList/add.do")		// 북마크 추가하기
public class LikeListAddController implements Controller, DataBinding {
	
	LikeListDao likeListDao = null;
	
	public LikeListAddController setLikeListnDao(LikeListDao likeListDao) {
		this.likeListDao = likeListDao;
		return this;
	}


	@Override
	public String execute(Map<String, Object> model) throws Exception {
		LikeList likeList = (LikeList)model.get("likeList");
		if(likeList.getId() == null || likeList.getId() == "") {
			return "/auth/login.do";
		}else {
			likeListDao.insertLike(likeList);
			return "redirect:list.do";		// 추가한 후 북마크리스트 리턴
		}
		
	}
	@Override
	public Object[] getDataBinders() {
		// key값 이름, 자동으로 생성해야 할 클래스 타입
		return new Object[] {
			"likeList", spms.vo.LikeList.class
		};
	}

}
