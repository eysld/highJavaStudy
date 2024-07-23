package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {
	
		
		/**
		 * MemberVO객체에 담긴 데이터를 DB에 등록하기 위한 메서드
		 * @param mv 등록할 회원정보를 담은 MemberVO 객체
		 * @return 회원등록 작업이 성공하면 1, 실패하면 0이 반환됨
		 */
		public int registMember(MemberVO mv);

		/**
		 * MemberVO객체에 담긴 데이터를 DB에 수정하기 위한 메서드
		 * @param mv 수정할 회원정보를 담은 MemberVO 객체
		 * @return 회원수정 작업이 성공하면 1, 실패하면 0이 반환됨
		 */
		public int modifyMember(MemberVO mv);
		
		/**
		 * 회원정보가 존재하는지 체크하기 위한 메서드
		 * @param memId 존재여부 체크할 회원ID
		 * @return 상세 회원정보를 담은 MemberVO 객체
		 */
		public boolean checkMember(String memId);
		
		/**
		 * 회원 상세정보를 가져오기 위한 메서드
		 * @param memId 상세정보를 가져올 회원ID
		 * @return 회원정보가 존재하면 true, 존재하지 않으면 false 반환됨
		 */
		public MemberVO getMember(String memId);
		
		/**
		 * 회원정보를 삭제하기 위한 메서드
		 * @param memId 삭제할 회원ID
		 * @return 회원정보 삭제 성공하면 1, 실패하면 0 반환됨
		 */
		public int removeMember(String memId);
		
		/**
		 * 모든 회원정보를 가져오기 위한 메서드
		 * @return 모든 회원정보를 담은 List객체
		 */
		public List<MemberVO> displayMember();
		
		/**
		 * 회원정보를 검색하기 위한 메서드
		 * @param mv 검색할 회원정보를 담은 MemberVO객체
		 * @return 검색된 회원정보를 담은 List객체
		 */
		public List<MemberVO> searchMember(MemberVO mv);
		
		

}
