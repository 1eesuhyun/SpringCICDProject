package com.sist.web.mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;
@Repository
@Mapper
public interface BoardMapper {
	// 리스트
	@Select("SELECT no,name,subject,hit,TO_CHAR(regdate,'yyyy-mm-dd') as dbday "
			+ "FROM board_1 ORDER BY no DESC "
			+ "OFFSET #{start} ROWS FETCH NEXT 10 ROWS ONLY")
	public List<BoardVO> boardListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM board_1")
	public int boardTotalPage();
	// 자동 증가번호(시퀀스)
	@SelectKey(keyProperty = "no",resultType = int.class,before = true, statement = "SELECT NVL(MAX(no)+1,1) as no FROM board_1")
	// 데이터 추가
	@Insert("INSERT INTO board_1 VALUES(#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0)")
	public void boardInsert(BoardVO vo);
	// 데이터 상세보기
	@Update("UPDATE board_1 SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void boardHitIncrement(int no);
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'yyyy-mm-dd') as dbday "
			+ "FROM board_1 "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	// 데이터 수정
	@Update("UPDATE board_1 SET "
			+ "name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	// 데이터 삭제
	@Select("SELECT pwd FROM board_1 WHERE no=#{no}")
	public String boardGetPassword(int no);
	@Delete("DELETE FROM board_1 WHERE no=#{no}")
	public void boardDelete(int no);
}
