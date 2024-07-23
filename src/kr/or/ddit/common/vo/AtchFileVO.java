package kr.or.ddit.common.vo;

import java.time.LocalDate;

public class AtchFileVO {
	
	private long atchFileId = -1; // 첨부파일ID
	private LocalDate createDt; // 생성일자
	private String useAt;; // 사용여부
	private int fileSn = 1; // 파일 순번
	private String fileStreCours; // 파일 저장경로
	private String streFileNm; // 저장 파일명
	private String orignlFileNm; // 원본 파일명
	private String fileExtsn; // 파일 확장자
	private String fileCn; // 파일 내용(비고란)
	private long fileSize = 0; // 파일 크기

	public long getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}

	public LocalDate getCreateDt() {
		return createDt;
	}

	public void setCreateDt(LocalDate createDt) {
		this.createDt = createDt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public int getFileSn() {
		return fileSn;
	}

	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}

	public String getFileStreCours() {
		return fileStreCours;
	}

	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	public String getStreFileNm() {
		return streFileNm;
	}

	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}

	public String getOrignlFileNm() {
		return orignlFileNm;
	}

	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	public String getFileExtsn() {
		return fileExtsn;
	}

	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	public String getFileCn() {
		return fileCn;
	}

	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "AtchFileVO [atchFileId=" + atchFileId + ", createDt=" + createDt + ", useAt=" + useAt + ", fileSn="
				+ fileSn + ", fileStreCours=" + fileStreCours + ", streFileNm=" + streFileNm + ", orignlFileNm="
				+ orignlFileNm + ", fileExtsn=" + fileExtsn + ", fileCn=" + fileCn + ", fileSize=" + fileSize + "]";
	}
	

}
