package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.LikeListDao;
import spms.dao.MemberDao;
import spms.dao.ReservationDao;
import spms.vo.LikeList;

@Component("/likeList/list.do")		// 북마크 리스트
public class LikeListController implements Controller {

	LikeListDao likeListDao;
	
	public LikeListController setlikeListDao(LikeListDao likeListDao) {
		this.likeListDao = likeListDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
			model.put("likeList", likeListDao.selectList());
			return "/detailPage/LikeList.jsp";
		
	}
	


}
