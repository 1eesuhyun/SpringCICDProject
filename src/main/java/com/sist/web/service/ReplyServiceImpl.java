package com.sist.web.service;
import java.util.*;
import org.springframework.stereotype.Service;
import com.sist.web.mapper.*;
import com.sist.web.vo.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	private final ReplyMapper mapper;

	@Override
	public List<ReplyVO> replyListData(int cno, int type) {
		// TODO Auto-generated method stub
		return mapper.replyListData(cno,type);
	}

	@Override
	public void replyInsert(ReplyVO vo) {
		// TODO Auto-generated method stub
		mapper.replyInsert(vo);
	}
}
