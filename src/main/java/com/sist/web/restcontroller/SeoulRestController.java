package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.sist.web.service.*;
import java.util.*;
import com.sist.web.vo.*;
@RestController
@RequiredArgsConstructor
public class SeoulRestController {
	private final SeoulService sservice;
	private String[] tables= {"","seoul_location","seoul_nature","seoul_shop"};
	// ResponseEntity => 비동기적으로 처리
	@GetMapping("/seoul/list_vue/")
	public ResponseEntity<Map> seoul_list(@RequestParam("page") int page,@RequestParam("type") int type)
	{
		Map map=new HashMap();
		try
		{	
			map.put("table_name", tables[type]);
			map.put("start", (page-1)*6);
			
			List<SeoulVO> list=sservice.seoulListData(map);
			int totalpage=sservice.seoulTotalPage(map);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map=new HashMap();
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("list", list);
			map.put("totalpage", totalpage);
			map.put("curpage", page);
			map.put("type", type);
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
