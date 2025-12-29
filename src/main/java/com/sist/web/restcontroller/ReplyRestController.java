package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;

import jakarta.servlet.http.HttpSession;

import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class ReplyRestController {
	private final ReplyService rservice;
	
	@GetMapping("/reply/list_vue/")
	public ResponseEntity<Map> reply_list_vue(@RequestParam("cno")int cno,@RequestParam("type")int type,HttpSession session)
	{
		Map map=new HashMap();
		try
		{
			List<ReplyVO> list=rservice.replyListData(cno, type);
			map.put("rList", list);
			map.put("type", type);
			map.put("cno", cno);
			map.put("sessionId", session.getAttribute("id"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@PostMapping("/reply/insert_vue/")
	public ResponseEntity<Map> reply_insert_vue(@RequestBody ReplyVO vo,HttpSession session)
	{
		Map map=new HashMap();
		try
		{
			String id=(String)session.getAttribute("id");
			String name=(String)session.getAttribute("name");
			vo.setId(id);
			vo.setName(name);
			rservice.replyInsert(vo);
			
			List<ReplyVO> list=rservice.replyListData(vo.getCno(), vo.getType());
			map.put("rList", list);
			map.put("cno", vo.getCno());
			map.put("type", vo.getType());
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
