package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("joinform".equals(actionName)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
			
		}else if("join".equals(actionName)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			UserVo vo = new UserVo(name, email, password, gender);
			
			UserDao dao = new UserDao();
			dao.insert(vo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");

		}else if("modify".equals(actionName)) {
			System.out.println("도착!");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");
			UserVo vo = new UserVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setGender(gender);
			
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			int no = authUser.getNo();
			vo.setNo(no);
			
			UserDao dao = new UserDao();
			dao.update(vo);
			
			authUser.setName(name);  //헤더에서 db까지 저장되고 바뀌는 글을 위해 설정 '--님 안녕하세요^^'
			
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
			
		}else if("modifyform".equals(actionName)) {
		
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int no = authUser.getNo();
			
			UserDao dao = new UserDao();
			UserVo userVo = dao.getUser(no);
			
			request.setAttribute("userVo", userVo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			
		}else if("loginform".equals(actionName)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			
		}else if("login".equals(actionName)) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			UserDao dao = new UserDao();
			UserVo vo = dao.getUser(email, password);
			
			
			if(vo==null) {                                     //유지시키는 세션
				System.out.println("실패");                     //그리고 메인으로 리다이렉트 
				WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail"); //로그인 실패시 fail이라는 꼬리표 달아주기
			} else {
				System.out.println("성공");
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", vo);
				
				WebUtil.redirect(request, response, "/mysite/main");
				return;                                         //
			}
			
		}else if("logout".equals(actionName)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();
			
			WebUtil.redirect(request, response, "/mysite/main");
			
		}else {
			
			WebUtil.redirect(request, response, "/mysite/main");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
