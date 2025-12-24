package com.sist.web.service;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.mapper.*;
@Service
@RequiredArgsConstructor
public class BusanServiceImpl implements BusanService{
	private final BusanMapper mapper;

	@Override
	public List<BusanVO> busanListData(Map map) {
		// TODO Auto-generated method stub
		return mapper.busanListData(map);
	}

	@Override
	public int busanTotalPage(Map map) {
		// TODO Auto-generated method stub
		return mapper.busanTotalPage(map);
	}
	
}
