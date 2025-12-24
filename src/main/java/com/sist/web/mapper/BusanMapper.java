package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;
@Repository
@Mapper
public interface BusanMapper {
	/*
	 * <select id="busanListData" resultType="com.sist.web.vo.BusanVO" parameterType="hashmap">
		 SELECT no,title,image1,hit,address,contenttype,contentid
		 FROM busantravel
		 <include refid="where-sql"/>
		 ORDER BY no ASC
		 OFFSET #{start} ROWS FETCH NEXT 6 ROWS ONLY
		</select>
		<select id="busanTotalPage" resultType="int" parameterType="hashmap">
		 SELECT CEIL(COUNT(*)/6.0) FROM busantravle
		 <include refid="where-sql"/>
	 </select>
	 */
	public List<BusanVO> busanListData(Map map);
	public int busanTotalPage(Map map);
}
