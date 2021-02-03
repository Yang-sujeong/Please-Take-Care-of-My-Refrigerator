package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ReviewDao;
import spms.vo.Review;

@Component("/review/add.do")		// 리뷰추가하기
public class ReviewAddController implements Controller, DataBinding {

	ReviewDao reviewDao = null;

	public ReviewAddController setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
		return this;
	}


	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Review review = (Review)model.get("review");
		if(review.getId() == null || review.getId() == "") {
			return "redirect:../auth/login.do";
			
		}else {
			reviewDao.insertRev(review);
			return "redirect:../detailPage/list.do?shop_num="+review.getShop_num();		// 해당식당 상세페이지 리턴
		}
	
	}
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
				"review" , spms.vo.Review.class
		};
	}

}
