package com.sist.web.vo;
import lombok.Data;
import java.util.*;
@Data
public class ReplyVO {
	private int no,cno,type;
	private String name,id,msg,dbday;
	private Date regdate;
}
