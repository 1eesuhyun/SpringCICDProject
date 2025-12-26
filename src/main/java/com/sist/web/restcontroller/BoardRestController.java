package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

@RestController
@RequiredArgsConstructor
public class BoardRestController {
	private final BoardService bservice;
	// 리스트
	@GetMapping("/board/list_vue/")
	public ResponseEntity<Map> board_list(@RequestParam("page")int page)
	{
		Map map=new HashMap();
		try
		{
			
			List<BoardVO> list=bservice.boardListData((page-1)*10);
			int totalpage=bservice.boardTotalPage();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	/*
	 * @ReqestBody => JSON을 객체로 변환
	 *             => javascript에서 전송된 값
	 * @ModelAttribute => Spring 자체에서 처리
	 *    | 커맨드 객체값 받는 경우
	 *      ------- VO
	 * @RequestParam => getParameter()
	 * 
	 * => mybatis : proceduer / function / trigger
	 *              ------------------------------
	 *              PL/SQL : 호불호
	 *                       ERP
	 *              => 댓글
	 *              => JOIN / SUBQUERY => Function
	 *              => replycount / likdcount => Trigger
	 *  => 동적 쿼리 : 검색
	 *  ----------------------------------------------- JPA
	 */
	// 작성
	@PostMapping("/board/insert_vue/")
	public ResponseEntity<Map> board_insert(@RequestBody BoardVO vo)
	{
		Map map=new HashMap();
		try
		{
			bservice.boardInsert(vo);
			map.put("vo", vo);
			map.put("msg", "yes");
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/board/detail_vue/")
	public ResponseEntity<BoardVO> board_detail(@RequestParam("no")int no)
	{
		BoardVO vo=new BoardVO();
		try
		{
			vo=bservice.boardDetailData(no);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	} 
	@DeleteMapping("/board/delete_vue/")
	public ResponseEntity<Map> board_delete(@RequestParam("no")int no,@RequestParam("pwd")String pwd)
	{
		Map map=new HashMap();
		try
		{
			String res=bservice.boardDelete(no, pwd);
			map.put("msg", res);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	// 수정
	@GetMapping("/board/update_vue/")
	public ResponseEntity<BoardVO> board_update(@RequestParam("no")int no)
	{
		BoardVO vo=new BoardVO();
		try
		{
			vo=bservice.boardUpdateData(no);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
	@PutMapping("/board/update_ok_vue/")
	public ResponseEntity<Map> board_update_ok(@RequestBody BoardVO vo)
	{
		Map map=new HashMap();
		try
		{
			String res=bservice.boardUpdate(vo);
			map.put("msg", res);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
