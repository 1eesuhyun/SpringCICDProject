package com.sist.web.service;
import java.util.*;
import com.sist.web.vo.SeoulVO;
/*
 *  DAO => table 1개 제어 => 순수하게 SQL문장만 수행
 *         ---------------------------------- repository
 *  Service = DAO 여거래 동시에 제어 / 추가 기능을 수행
 *      | BI
 */
public interface SeoulService {
	public List<SeoulVO> seoulListData(Map map);
	public int seoulTotalPage(Map map);
}
