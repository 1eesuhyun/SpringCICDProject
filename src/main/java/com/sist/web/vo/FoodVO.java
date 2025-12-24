package com.sist.web.vo;
import lombok.Data;
/*
 *  Vue 가 편한거
 *  1. Find
 *  2. 결제
 *  3. 예약
 *  4. 추천
 *  5. 로그인
 *  6. ======> 수정 / 삭제
 *  7. 실시간(채팅)
 *  =============> 일반 JSP
 */
@Data
public class FoodVO {
	private int fno,hit,jjimcount,likecount,replycount;
	private String name,type,phone,address,theme,price,time,parking,poster,images,content;
	private Double score;
}
