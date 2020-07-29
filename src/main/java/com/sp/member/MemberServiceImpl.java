package com.sp.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.common.dao.CommonDAO;

@Service("member.memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private CommonDAO dao;

	@Override
	public void insertMember(Member dto) throws Exception {
		
		try {
			dao.insertData("member.insertMember", dto);			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateMember(Member dto) throws Exception {
		
		try {
			dao.updateData("member.updateMember");			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public void deleteMember(Member dto) throws Exception {
		
		try {
			dao.deleteData("member.deleteMember");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	@Override
	public Member readMember(String userId) {
		Member dto = null;
		
		try {
			dto = dao.selectOne("member.readMember", userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int dataCount(Map<String, Object> map) {
		int result = 0;
		
		return result;
	}

	@Override
	public List<Member> listMember(Map<String, Object> map) {
		List<Member> list = null;
		
		return list;
	}
	
}
