package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImple;

@WebServlet("/member/delete.do")
public class RemoveMemberController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		IMemberService memService = MemberServiceImple.getInstance();
		
		int cnt = memService.removeMember(memId);
		
		String msg = "";
		
		if(cnt > 0) {
			// 삭제 성공
			msg = "성공";
		}else {
			// 삭제 실패
			msg = "실패";
		}
		
		req.getSession().setAttribute("msg", msg);
		
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);
		
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
