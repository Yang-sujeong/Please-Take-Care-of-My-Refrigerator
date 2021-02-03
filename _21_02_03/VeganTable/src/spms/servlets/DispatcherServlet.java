package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.bind.ServletRequestDataBinders;
import spms.context.ApplicationContext;
import spms.controls.Controller;
import spms.listeners.ContextLoaderListener;

/* 1) 의미 : tomcat으로 부터 바로 '제어 controller'로 전송하던 방식
 *      -> * tomcat은 Front controller로 전송
 *           Front controller : DispatcherServlet
 *         * Front controller는 PageController로 분기
 *           back  controller : PageController
 *      역할을 분할
 * 2) 이유 : 
 *     2-1) 역할을 보다 상세히 나눠서 규격화/자동화(Framework)하려고 한다
 *     2-2) 스프링 프레임워크의 제작 방식
 *     2-3) Front Controller만 HttpServlet의 상속을 받고
 *          Page Controller는 일반 자바 클래스로 전환(POJO-Plain Old Java Object)
 * 
 * 3) Front Controller(DispatcherServlet)의 역할
 *     3-1) tomcat(서블릿 컨테이너)과 연결되어 다른 Page Controller에 분기
 *     3-2) Page Controller에 전송할 VO 객체 생성 (향후 자동화)
 *     3-3) 서블릿/jsp 이동 처리
 *     3-4) Page Controller에서 발생하는 예외처리
 *     
 * 4) 장점
 *     4-1) 다른 WAS 이삭할 때 Front Controller만 수정하면 된다
 *          (Front Controller만 HttpServlet을 상속받으므로)
 *     4-2) 브라우저한테 노출하고 싶은 않은 페이지를 감출 수 있다
 *     4-3) Page Controller가 하는 공통 역할을 Front Controller에 담았으므로
 *          자동화(Framework)하기 편리하다
 *            
 * 
 * */

@WebServlet("*.do")
@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		// pageController에 분기 전송을 위해 '서블릿 경로'를 얻는다
		String servletPath = request.getServletPath();

		try {
			ApplicationContext ctx = 
					ContextLoaderListener.getApplicationContext();
			Controller pageController = 
					(Controller)ctx.getBean(servletPath);
					
			
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());

			// pageController객체가 DataBinding의 상속을 받았다면
			// => 만들어야 할 객체가 존재하는 pageController라면
			if(pageController instanceof DataBinding) {
				// pageController에 전달할 객체를 생성하여
				// 브라우저가 보내는 파라미터를 객체에 자동 주입하여
				// model 객체에 저장한다
				prepareRequestData(request, model,
						(DataBinding)pageController);
			}

			String viewUrl = "";
			// POJO Page Controller
			if (pageController != null) {
				viewUrl = pageController.execute(model);
				for (String key : model.keySet())
					request.setAttribute(key, model.get(key));
			}else {
				System.out.println("주소 대상 Controller가 없습니다!");
			}

			// 경로가 'redirect:'로 시작하면 바로 이동
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
	
	private void prepareRequestData(HttpServletRequest request,
									HashMap<String, Object> model,
									DataBinding dataBinding)
									throws Exception{
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null; 	// key이름
		Class<?> dataType = null;	// 생성할 객체의 클래스 정보
		Object dataObj = null;		// 생성한 객체
		for(int i=0;i<dataBinders.length;i+=2) {
			dataName = (String)dataBinders[i];
			dataType = (Class<?>)dataBinders[i+1];
			
			/* request : 매개변수 추출을 위해 필요
			 * dataType : 객체를 생성할 클래스 타입
			 * dataName : 매개변수 이름
			 * */
			// 객체를 생성해준다
			dataObj = ServletRequestDataBinders.bind(
						request, dataType, dataName);
			// 만들어진 객체를 model에 저장한다
			model.put(dataName, dataObj);
		}
	}
}









