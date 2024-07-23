package kr.or.ddit.common.dao;

import java.util.List;

import kr.or.ddit.common.vo.AtchFileVO;

public interface IAtchFileDao {

	/**
	 * 첨부파일 저장
	 * @param atchFileVO
	 * @return
	 */
	public int insertAtchFile(AtchFileVO atchFileVO);
	
	/**
	 * 세부 첨부파일 정보 저장
	 * @param atchFileVO
	 * @return
	 */
	public int insertAtchFileDetail(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 목록 조회
	 * @param atchFileVO
	 * @return
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO);
	
	/**
	 * 첨부파일 세부정보 조회
	 * @param atchFileVO
	 * @return
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO);
}
