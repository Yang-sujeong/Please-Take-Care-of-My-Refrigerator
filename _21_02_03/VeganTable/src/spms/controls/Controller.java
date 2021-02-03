package spms.controls;

import java.util.Map;

/* 1) Front Controller에서 Page Controller를 동일한 방식으로 호출하기 위해
 * 정의한 인터페이스
 * 2) 모든 Page Controller는 이 인터페이스를 상속받는다
 * 3) Page Controller의 Servlet 종속을 제거하고 POJO 방식으로 만든다 
 * */
public interface Controller {
	public String execute(Map<String, Object> model) throws Exception;
}
