package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.ReservationDao;
import spms.dao.ShopDao;
import spms.vo.Member;
import spms.vo.Shop;

@Component("/Allshop/list.do")		// 전체 목록 리스트 
public class AllShopListController implements Controller{

	ShopDao shopDao;
	
	public AllShopListController setAllshopListDao(ShopDao shopDao) {
		this.shopDao = shopDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String shop_num = (String)model.get("shop_num");
		model.put("shop", shopDao.selectList());
		
		return "/detailPage/Shoplist.jsp";		// 상세페이지로 리턴
		
	}

}
