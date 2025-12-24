package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.web.service.*;
@RestController
@RequiredArgsConstructor
public class BusanRestController {
	private final BusanService bservice;
	
	@GetMapping("/busan/list_vue/")
	public ResponseEntity<Map> busan_list(@RequestParam("page")int page,@RequestParam("type") int type)
	{
		Map map=new HashMap();
		try
		{
			map.put("type", type);
			map.put("start", (page-1)*6);
			
			List<BusanVO> list=bservice.busanListData(map);
			int totalpage=bservice.busanTotalPage(map);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map=new HashMap();
			map.put("list", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("type", type);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
