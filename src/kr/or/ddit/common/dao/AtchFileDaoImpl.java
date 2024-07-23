package kr.or.ddit.common.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.vo.AtchFileVO;
import kr.or.ddit.util.MyBatisUtil;

public class AtchFileDaoImpl implements IAtchFileDao {

	private static IAtchFileDao fileDao;
	
	private AtchFileDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static IAtchFileDao getInstance() {
		if(fileDao == null) {
			fileDao = new AtchFileDaoImpl();
		}
		return fileDao;
	}
	
	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getSqlSession();
		
		int cnt = 0;
		
		try {
			cnt = session.insert("atchFile.insertAtchFile", atchFileVO);
			
			if(cnt > 0) {
				session.commit();
			}
			
			
		}catch (PersistenceException e) {
			session.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int insertAtchFileDetail(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getSqlSession();

		int cnt = 0;

		try {
			cnt = session.insert("atchFile.insertAtchFileDetail", atchFileVO);

			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getSqlSession(true);	// true면 autoCommit

		List<AtchFileVO> atchFileList = new ArrayList<AtchFileVO>();

		try {
			atchFileList = session.selectList("atchFile.getAtchFileList", atchFileVO);

		} catch (PersistenceException e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		SqlSession session = MyBatisUtil.getSqlSession(true);
		
		AtchFileVO fileVO = null;
		
		try {
			
			fileVO = session.selectOne("atchFile.getAtchFileDetail", atchFileVO);
			
		}catch(PersistenceException e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return fileVO;
	}
	
	public static void main(String[] args) {
		IAtchFileDao fileDao = AtchFileDaoImpl.getInstance();
		
		AtchFileVO fileVO = new AtchFileVO();
		
		int cnt = fileDao.insertAtchFile(fileVO);
		
		if(cnt > 0) {
			System.out.println("등록 성공");
			System.out.println("AtchFileId => " + fileVO.getAtchFileId());
			
			
			
			fileVO.setFileStreCours("d:/D_Other/hello.jpg");
			fileVO.setStreFileNm("abcde");
			fileVO.setOrignlFileNm("hello.jpg");
			fileVO.setFileExtsn("jpg");
			fileVO.setFileCn("");
			fileVO.setFileSize(100000);
			
			fileDao.insertAtchFileDetail(fileVO);
			fileVO.setFileStreCours("d:/D_Other/hello.jpg");
			fileVO.setStreFileNm("abcde");
			fileVO.setOrignlFileNm("hello.jpg");
			fileVO.setFileExtsn("jpg");
			fileVO.setFileCn("");
			fileVO.setFileSize(100000);
			
			int cnt2 = fileDao.insertAtchFileDetail(fileVO);
			
			if(cnt2 > 0) {
				System.out.println("파일 상세테이블 등록 성공");
			}else {
				System.out.println("파일 상세테이블 등록 실패");
			}
			
			List<AtchFileVO> atchFileList = fileDao.getAtchFileList(fileVO);
			System.out.println("atchFileList size : " + atchFileList.size());
			
			fileVO = fileDao.getAtchFileDetail(fileVO);
			
			System.out.println("조회된 첨부파일 상세정보 => " + fileVO);
			
		}else {
			System.out.println("등록 실패");
		}
	}
}
