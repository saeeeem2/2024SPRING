package com.myweb.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService msv;
	private final BCryptPasswordEncoder bcEncoder;

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		log.info(">>>>register <<< mvo>>>", mvo);
		int isOk = msv.insertMember(mvo);
		return "index";
	}

	@GetMapping("/login")
	public void login() {
	}

	@PostMapping("/login") 
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		//로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		//다시 로그인하라고 유도
		re.addAttribute("email",request.getAttribute("email"));
		re.addAttribute("errMsg",request.getAttribute("errMsg"));
		return "redirect:/member/login";
	 }
	
	//@RequestParam("email") : 쿼리스트링(파라미터 받기)
	@GetMapping("/modify")
	public void modify(Principal p, Model m) {
		log.info(">>>>principal >>>> email : "+p.getName());
		String email=p.getName();
		m.addAttribute("mvo", msv.detail(email));
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response) {
		if(mvo.getPwd().isEmpty()) {
			//비번 없는 업데이트
			msv.modify(mvo);
		} else {
			//비번을 다시 세팅해서 인코딩 후 업데이트 진행
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
			msv.modifyPwd(mvo);
		}	
		//로그아웃 진행
		logout(request,response);

		return "/member/login";
	}
	
	@GetMapping("/remove")
	public String removeMember(@RequestParam("email")String email,HttpServletRequest req, HttpServletResponse res) {
		int isOk=msv.remove(email);
		logout(req,res);
		return "index";
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		List<MemberVO> list=msv.getList();
		m.addAttribute("mvo",list);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); //현재 로그인 되어있는 계정
		new SecurityContextLogoutHandler().logout(request, response, authentication);
	}
	
}
