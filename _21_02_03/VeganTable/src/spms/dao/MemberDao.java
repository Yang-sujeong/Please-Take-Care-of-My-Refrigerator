package spms.dao;

import java.util.List;

import spms.vo.Member;

/* 굳이 MemberDao 인터페이스를 만든 이유는?
 * 
 * 향후 Oracle, SQL-Server, ...... 확장해 나갈때
 * 이 인터페이스를 상속받아서 기능은 각 DBMS별로 특성에 맞게 구현하되
 * 메서드의 역할/리턴값은 동일하게 해줌으로써
 * DBMS의 교체를 매우 쉽게 해준다
 * */
public interface MemberDao {

	public List<Member> selectList() throws Exception;
	public int insert(Member member) throws Exception;
	
	public int delete(String id) throws Exception;
		
	public Member selectOne(String no) throws Exception;
	public int update(Member member) throws Exception;
	 
	public Member exist(String id, String pw) throws Exception;
	
}
