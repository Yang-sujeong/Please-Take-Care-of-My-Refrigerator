package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

@Component("/member/update.do")		// 회원정보 수정
public class MemberUpdateController implements Controller, DataBinding {
	
	MemberDao memberDao = null;
	
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");
		String id = (String)model.get("id");
		
			memberDao.update(member);
			return "redirect:mypage.do?id="+id;		// 수정 후 마이페이지 리턴
		
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"id", String.class,
			"member", spms.vo.Member.class
		};
	}

}












