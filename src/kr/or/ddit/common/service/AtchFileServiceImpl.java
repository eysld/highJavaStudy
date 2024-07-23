package kr.or.ddit.common.service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.common.dao.AtchFileDaoImpl;
import kr.or.ddit.common.dao.IAtchFileDao;
import kr.or.ddit.common.vo.AtchFileVO;

/**
 * 공통 첨부파일을 처리하기 위한 서비스
 * @author PC-02
 *
 */
public class AtchFileServiceImpl implements IAtchFileService {

	private static IAtchFileService fileService;

	private IAtchFileDao fileDao;
	
	public AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if (fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		return fileService;
	}


	@Override
	public AtchFileVO saveAtchFileList(Collection<Part> parts) throws Exception {
		
		String uploadPath = "d:/D_Other/upload_files";
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		
		boolean isFirstFile = true;	// 첫 번째 파일인지 여부 체크
		
		for(Part part : parts) {
			System.out.println("Content-Disposition => " + part.getHeader("Content-Disposition"));
			
			String fileName = part.getSubmittedFileName(); // 전송된 파일명 가져오기
			
			if(fileName != null && !fileName.equals("")) {
				// 폼필드가 아니거나 파일명이 존재하는 경우 (첨부파일이 존재하는 경우)
				
				if(isFirstFile) {	// 첫 번째 첨부파일인 경우
					
					isFirstFile = false;
					// 기본 첨부파일 정보 저장
					atchFileVO = new AtchFileVO();
					fileDao.insertAtchFile(atchFileVO);
					
				}
				
				//////////////////////////////////////////////////////
				
				String orignFileName = fileName; 	// 원본파일명
				long fileSize = part.getSize();		// 파일크기
				String saveFileName = "";			// 저장파일명
				String saveFilePath = "";			// 저장파일경로
				
				saveFileName = UUID.randomUUID().toString().replace("-", "");
				saveFilePath = uploadPath + "/" + saveFileName;
				
				// 확장자 추출하기
				String fileExtension = orignFileName.lastIndexOf(".") < 0 ? "" 
								: orignFileName.substring(orignFileName.lastIndexOf(".") + 1) ;
				
				part.write(saveFilePath); // 파일 저장
				
				atchFileVO.setFileStreCours(saveFilePath);
				atchFileVO.setStreFileNm(saveFileName);
				atchFileVO.setOrignlFileNm(orignFileName);
				atchFileVO.setFileExtsn(fileExtension);
				atchFileVO.setFileSize(fileSize);
				atchFileVO.setFileCn("");
				
				fileDao.insertAtchFileDetail(atchFileVO);
				
				part.delete(); 	// 임시 업로드 파일 삭제하기
				
			}
		}
		
		
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		return fileDao.getAtchFileList(atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		return fileDao.getAtchFileDetail(atchFileVO);
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}

}
