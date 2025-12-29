package com.sist.web.restcontroller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;

@RestController
@RequiredArgsConstructor
public class FoodRestController {
	private final FoodService fservice;
	
	@GetMapping("/food/find_vue/")
	public ResponseEntity<Map> food_find(@RequestParam("page") int page,@RequestParam("address")String address)
	{
		Map map=new HashMap();
		try
		{
			List<FoodVO> list=fservice.foodFindData((page-1)*12, address);
			int totalpage=fservice.findTotalPage(address);
			
			final int block=10;
			int startPage=((page-1)/block*block)+1;
			int endPage=((page-1)/block*block)+block;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("curpage", page);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			map.put("totalpage", totalpage);
			map.put("list", list);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	@GetMapping("/food/find_detail_vue/")
	public ResponseEntity<FoodVO> food_detail_vue(@RequestParam("fno")int fno)
	{
		FoodVO vo=new FoodVO();
		try
		{
			vo=fservice.foodDetailData(fno);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
}
