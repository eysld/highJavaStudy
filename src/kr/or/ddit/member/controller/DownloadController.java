package kr.or.ddit.member.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.common.service.AtchFileServiceImpl;
import kr.or.ddit.common.service.IAtchFileService;
import kr.or.ddit.common.vo.AtchFileVO;

@WebServlet("/download.do")
public class DownloadController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		long atchFileId = req.getParameter("fileId") == null ? -1 : Long.parseLong(req.getParameter("fileId"));
		int fileSn = req.getParameter("fileSn") == null ? -1 : Integer.parseInt(req.getParameter("fileSn"));
		
		// 파일 상세정보 조회
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setAtchFileId(atchFileId);
		atchFileVO.setFileSn(fileSn);
		
		atchFileVO = fileService.getAtchFileDetail(atchFileVO);
		
		////////////////////////////////////////////////////////
		
		/*
		 	[Content-Disposition 헤더]
		 	
		 	응답메세지의 Header에서 사용되는 경우 ex) 파일 다운로드
		 	
		 	Content-Disposition : inline (default)
		 	Content-Disposition : attachment 						// 파일 다운로드 처리
		 	Content-Disposition : attachment; filename="hello.jpg"	// 파일 다운로드 처리
		 	
		 */
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\""
		// URL에는 공백문자를 포함할 수 없다. URLEncoding을 이용하여 인코딩 작업을 하면 
		// 공백문자는 (+)로 표시되기 때문에 + 문자를 공백문자인 %20으로 바꿔준다.
				
				+ URLEncoder.encode(atchFileVO.getOrignlFileNm(), "UTF-8").replaceAll("\\+", "%20")
				+ "\"");
		
		BufferedInputStream bis = new BufferedInputStream(
									new FileInputStream(atchFileVO.getFileStreCours()));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int data = 0;
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
