package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;

import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class MemberRestController {
	private final MemberService mservice;
	
	@GetMapping("/member/login_vue/")
	public ResponseEntity<MemberVO> login_vue(@RequestParam("id")String id,@RequestParam("pwd") String pwd,HttpSession session)
	{
		MemberVO vo=new MemberVO();
		try
		{
			vo=mservice.isLogin(id, pwd);
			if(vo.getMsg().equals("OK"))
			{
				session.setAttribute("id",vo.getId());
				session.setAttribute("name", vo.getName());
				session.setAttribute("sex", vo.getSex());
				session.setAttribute("address", vo.getAddress());
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
	@GetMapping("/member/logout_vue/")
	public void member_logout_vue(HttpSession session)
	{
		session.invalidate();
	}
}
