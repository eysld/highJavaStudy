package kr.or.ddit.member.service;

import java.util.List;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImple implements IMemberService {

	private static IMemberService memService;
	
	
	private IMemberDao memDao;
	
	private MemberServiceImple() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImple();
		}
		return memService;
	}
	
	@Override
	public int registMember(MemberVO mv) {
		int cnt = memDao.insertMember(mv);
		
		if(cnt>0) {
			// 정상회원등록 알림 메일 발송
			
		}
		return cnt;
	}

	@Override
	public int modifyMember(MemberVO mv) {
		int cnt = memDao.updateMember(mv);
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		return memDao.checkMember(memId);
	}

	@Override
	public int removeMember(String memId) {
		int cnt = memDao.deleteMember(memId);
		
		return cnt;
		
	}

	@Override
	public List<MemberVO> displayMember() {
		
		return memDao.getAllMember();
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		return memDao.searchMember(mv);
	}

	@Override
	public MemberVO getMember(String memId) {
		return memDao.getMember(memId);
	}

}
