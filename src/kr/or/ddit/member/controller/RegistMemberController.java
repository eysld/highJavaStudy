package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.common.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImple;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/insert.do")
@MultipartConfig
public class RegistMemberController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/member/insertForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		IMemberService memberService = MemberServiceImple.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		
		try {
			// 첨부파일 업로드 처리하기
			atchFileVO = fileService.saveAtchFileList(req.getParts());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		mv.setAtchFileId(atchFileVO.getAtchFileId());
		
		int cnt = memberService.registMember(mv);

		String msg = "";

		if (cnt > 0) {
			// 삭제 성공
			msg = "성공";
		} else {
			// 삭제 실패
			msg = "실패";
		}

		req.getSession().setAttribute("msg", msg);

//		req.getRequestDispatcher("/member/list.do").forward(req, resp);

		resp.sendRedirect(req.getContextPath() + "/member/list.do");

	}
}
