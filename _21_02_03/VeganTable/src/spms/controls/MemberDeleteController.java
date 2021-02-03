package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;

@Component("/member/delete.do")		// 회원탈퇴
public class MemberDeleteController implements Controller, DataBinding {
	
	MemberDao memberDao = null;
	
	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		String id = (String)model.get("id");
		memberDao.delete(id);	
		
		return "redirect:../auth/logout.do";
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"id", String.class
		};
	}

}








