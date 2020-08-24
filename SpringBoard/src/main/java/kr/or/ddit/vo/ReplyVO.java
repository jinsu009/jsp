package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private Integer rep_no;
	private Integer bo_no;
	private String rep_content;
	private String rep_writer;
	private String rep_date;
	private String rep_ip;
	private String rep_pass;
}
