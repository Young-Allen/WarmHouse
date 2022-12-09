package me.spring.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("preHandle执行");
//		System.out.println("url: " + request.getRequestURI());
		
		String passURI = request.getRequestURI();
		if(passURI.contains("login") || passURI.contains("toLogin") || passURI.contains("failed")
				|| passURI.contains("register") || passURI.contains("js") || passURI.contains("css")
				|| passURI.contains("img") || passURI.contains("toForget") || passURI.contains("getForgetCode")
				|| passURI.contains("changePassword") || passURI.contains("judge")) {
			return true;
		}
		
		HttpSession session = request.getSession();
//		System.out.println(session.getAttribute("user"));
		if(session.getAttribute("user") != null) {
			return true;
		}
		
		//没有登录跳转到登录页面
		request.getRequestDispatcher("/WEB-INF/template/login/login.jsp").forward(request, response);
		return false;
	}

}
