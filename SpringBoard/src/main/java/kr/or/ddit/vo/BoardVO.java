package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.UpdateGroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BoardVO implements Serializable{
	
	public BoardVO(@NotNull(groups = UpdateGroup.class) Integer bo_no, @NotBlank String bo_pass) {
		super();
		this.bo_no = bo_no;
		this.bo_pass = bo_pass;
	}
	
	private Integer rnum;
	@NotNull(groups = UpdateGroup.class)
	private Integer bo_no;
	@NotBlank
	private String bo_title;
	@NotBlank
	private String bo_writer;
	private String bo_date;
	private String bo_content;
	@NotBlank
	private String bo_pass;
	@NotBlank
	private String bo_ip;
	private Integer bo_hit;
	private Integer bo_parent;
	
	// 한번에 여러개의 파일을 업로드 하기위해 
	private MultipartFile[] bo_files;
	
	/**
	 * 걸러내기 위한 메소드 
	 * 비어있거나 null이면 그냥 넘어간다. 
	 * 파일의 이름이 비어있따 : 파일이 없다. > continue
	 * 실제 업로드가됐을때 : adapter pattern 
	 * 
	 */
	
	public void setBo_files(MultipartFile[] bo_files) {
		this.bo_files = bo_files;
		if(bo_files==null || bo_files.length==0) {
			return;
		}
		attatchList = new ArrayList<>();
		for(MultipartFile boFile : bo_files) {
			if(StringUtils.isBlank(boFile.getOriginalFilename())) {
				continue;
			}
			attatchList.add(new AttatchVO(boFile));
		}

	}
	
	private List<ReplyVO> replyList;
	private List<AttatchVO> attatchList;
	private int startAttNo;
	
	private int[] deleteAttatches;
	// 몇 개의 데이터를 지우는지 모르기 때문에 배열로 받는다. 
}
