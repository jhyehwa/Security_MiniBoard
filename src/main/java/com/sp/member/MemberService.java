package com.sp.member;

import java.util.List;
import java.util.Map;

public interface MemberService {
	public void insertMember(Member dto) throws Exception;
	public void updateMember(Member dto) throws Exception;
	public void deleteMember(Member dto) throws Exception;
	public Member readMember(String userId);
	public int dataCount(Map<String, Object> map);
	public List<Member> listMember(Map<String, Object> map);
}
