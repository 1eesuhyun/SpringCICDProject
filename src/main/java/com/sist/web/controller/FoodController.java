package com.sist.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fservice;
	
	@GetMapping("/food/list")
	public String food_list(@RequestParam(name="page",required = false)String page ,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowsize=12;
		int start=(curpage-1)*rowsize;
		List<FoodVO> list=fservice.foodListData(start);
		int totalpage=fservice.foodTotalPage();
		
		final int block=10;
		int startPage=((curpage-1)/block*block)+1;
		int endPage=((curpage-1)/block*block)+block;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list", list);
		
		model.addAttribute("main_jsp", "../food/list.jsp");
		return "main/main";
	}
	@GetMapping("/food/detail")
	public String food_detail(@RequestParam("fno")int fno,Model model)
	{
		FoodVO vo=fservice.foodDetailData(fno);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../food/detail.jsp");
		return "main/main";
	}
	@GetMapping("/food/find")
	public String food_find(Model model)
	{
		model.addAttribute("main_jsp", "../food/find.jsp");
		return "main/main";
	}
	// detail => pinia : 지도 / 댓글 => 로그인 처리
	@GetMapping("/food/detail_vue")
	public String food_detail_vue(@RequestParam("fno")int fno,Model model)
	{
		
		model.addAttribute("main_jsp", "../food/find_detail.jsp");
		return "main/main";
	}
}
