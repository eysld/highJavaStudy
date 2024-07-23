package kr.or.ddit.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImple;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/login/loginForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		IMemberService memService = MemberServiceImple.getInstance();
		
		// 해당 회원정보 가져오기
		MemberVO mv = memService.getMember(username);
		
		// DB에서 가져온 사용자 정보를 이용하여 패스워드 일치여부 확인하기
		boolean isAuthenticated = false;		// 인증된 사용자 여부
		
		if(mv != null) {
			if(password.equals(mv.getMemPass())) {
				isAuthenticated = true;
			}
		}
		
		if(isAuthenticated) {	// 사용자 인증 성공시
			
			// 세션에 로그인 정보 설정하기
			req.getSession().setAttribute("LOGIN_USER", mv);
			// 메인페이지 이동
			resp.sendRedirect(req.getContextPath() + "/main.do");
			
			
		}else {	// 사용자 인증 실패시
			
			// 로그인 화면으로 이동
			resp.sendRedirect(req.getContextPath() + "/login.do");
			
		}
		
	}
}
