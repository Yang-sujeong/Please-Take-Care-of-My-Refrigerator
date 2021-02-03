package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/member/mypage.do")		// 회원정보 확인
public class MemberListController implements Controller, DataBinding {

	MemberDao memberDao = null;
	
	public MemberListController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String id = (String)model.get("id");
		
			model.put("member", memberDao.selectOne(id));
			return "/member/MyPage.jsp";
		
			
	}
	
	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"id", String.class,
			"member", spms.vo.Member.class
		};
	}

}
