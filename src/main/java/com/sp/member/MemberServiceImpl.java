package com.sp.member;

import java.util.List;

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

			dto.setAuthority("ROLE_USER");
			dao.insertData("member.insertAuthority", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void updateMember(Member dto) throws Exception {

		try {
			dao.updateData("member.updateMember", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void deleteMember(String userId) throws Exception {

		try {
			dao.deleteData("member.deleteMember", userId);
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
	public void insertAuthority(Member dto) throws Exception {

		try {
			dao.insertData("member.insertAuthority", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public void updateAuthority(Member dto) throws Exception {

		try {
			dao.updateData("member.updateAuthority", dto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	@Override
	public List<Member> listAuthority(String userId) {
		List<Member> list = null;

		try {
			list = dao.selectList("member.listAuthority", userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
