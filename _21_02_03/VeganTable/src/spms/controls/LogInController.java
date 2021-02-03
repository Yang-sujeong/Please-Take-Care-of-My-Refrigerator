package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.dao.MySqlMemberDao;
import spms.vo.Member;

@Component("/auth/login.do")		// 로그인
public class LogInController implements Controller, DataBinding {
	
	MemberDao memberDao = null;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member loginInfo = (Member)model.get("loginInfo");
		
		if(loginInfo.getId() == null) {
			return "/auth/LogInForm.jsp";
		}else {
			
			Member member = memberDao.exist(loginInfo.getId(),
											loginInfo.getPw());
			if(member != null) {
				HttpSession session = (HttpSession)model.get("session");
				session.setAttribute("member", member);		// 세션에 id, pw, name이 올라가게됨 
				return "redirect:../Allshop/list.do";
			}else {
				return "/auth/LogInFail.jsp";
			}
		}
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {
			"loginInfo", spms.vo.Member.class	
		};
	}

}





