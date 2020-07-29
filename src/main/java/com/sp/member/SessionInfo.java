package com.sp.member;

// 세션에 저장할 정보(아이디, 이름, 권한등)
public class SessionInfo {
	private String userId;
	private String userName;
	private int memberLevel;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
}
