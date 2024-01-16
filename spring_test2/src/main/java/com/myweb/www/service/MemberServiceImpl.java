package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{
	
	private final MemberDAO mdao;

	@Transactional
	@Override
	public int insertMember(MemberVO mvo) {
		int isOk=mdao.insert(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		
		return mdao.updateLastLogin(authEmail)>0 ? true : false;
	}

	@Transactional
	@Override
	public MemberVO detail(String email) {
		MemberVO mvo=mdao.selectEmail(email);
		mvo.setAuthList(mdao.selectAuth(email));
		return mvo;
	}

	@Override
	public List<MemberVO> getList() {
		List<MemberVO> list=mdao.selectAllList();
		for(MemberVO mvo : list) {
			mvo.setAuthList(mdao.selectAuth(mvo.getEmail()));
		}
		return list;
	}

	@Override
	public void modify(MemberVO mvo) {
		mdao.modify(mvo);
	}

	@Override
	public void modifyPwd(MemberVO mvo) {
		mdao.modifyPwd(mvo);
	}

	@Transactional
	@Override
	public int remove(String email) {
		mdao.removeAuth(email);
		return mdao.remove(email);
	}

}
