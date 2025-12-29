package com.sist.web.service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.sist.web.mapper.*;
import com.sist.web.vo.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberMapper mapper;

	@Override
	public MemberVO isLogin(String id, String pwd) {
		// TODO Auto-generated method stub
		MemberVO vo=new MemberVO();
		int count=mapper.memberIdCheck(id);
		if(count==0)
		{
			vo.setMsg("NOID");
		}
		else
		{
			MemberVO dbVO=mapper.memberInfoData(id);
			if(pwd.equals(dbVO.getPwd()))
			{
				vo.setMsg("OK");
				vo.setId(dbVO.getId());
				vo.setSex(dbVO.getSex());
				vo.setAddress(dbVO.getAddress());
				vo.setName(dbVO.getName());
			}
			else
			{
				vo.setMsg("NOPWD");
			}
		}
		return vo;
	}
}
