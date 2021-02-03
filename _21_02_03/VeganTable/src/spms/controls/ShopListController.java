package spms.controls;

import java.util.List;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ReviewDao;
import spms.dao.ShopDao;
import spms.vo.Shop;


@Component("/detailPage/list.do")		// 상세페이지
public class ShopListController implements Controller,  DataBinding {

	ShopDao shopDao;
	ReviewDao reviewDao;
	
	public ShopListController setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
		return this;
	}
	public ShopListController setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String shop_num = (String) model.get("shop_num");
		Shop selectShop = shopDao.selectOneShop(shop_num);
		
		model.put("shop", selectShop);
		model.put("review", reviewDao.selectOneRev(shop_num));
		
		return "/detailPage/detailPage.jsp";
		
	}
	public Object[] getDataBinders() {
		return new Object[] {
			"shop_num", String.class
		};
	}

}
