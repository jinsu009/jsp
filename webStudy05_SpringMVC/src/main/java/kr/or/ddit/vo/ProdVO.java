package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.validate.stereotype.MimeChecker;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor // 4가지를 Data하나로 퉁칠수 있다.
@Data
@ToString(exclude={"prod_detail","prod_img"})
@EqualsAndHashCode(of={"prod_id"})

// 2020-05-26
public class ProdVO {
	
	@NotBlank(groups = UpdateGroup.class)
	private String prod_id;
	@NotBlank
	private String prod_name;
	@NotBlank
	private String prod_lgu;
	@NotBlank
	private String prod_buyer;
	@NotBlank
	private String prod_cost;
	@NotBlank
	private String prod_price;
	@NotBlank
	private String prod_sale;
	@NotBlank
	private String prod_outline;
	
	private String prod_detail;
	
	
	private String prod_img;
	
	@NotBlank
	@Min(0)
	private String prod_totalstock;
	private String prod_insdate;
	
	@NotBlank
	@Min(0)
	private String prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;

	@Min(0)
	private String prod_qtyin;
	
	@Min(0)
	private String prod_qtysale;
	
//	@Min(0)
//	@NotNull(groups = InsertGroup.class)
	private String prod_mileage;
	
	private BuyerVO buyer; // has a 관계
//	private LProdVO lprod;
	private String lprod_nm;
	
	private List<MemberVO> memberList; // has many 관계
//	private String mem_name;
	
	
//	private PartWrapper imageFile; // spring 
	
	@MimeChecker(contentType="image/*")
	private MultipartFile prod_image; // 이미지 자체가 들어가야한다. 
	public void setProd_image(MultipartFile	prod_image) {
		if(prod_image == null){	
			return;
		}
		String filename = prod_image.getOriginalFilename();
		if(filename==null || filename.isEmpty()) {
			// 파일이 업로드가 안됨 
			return;
		}
		this.prod_image = prod_image;
		prod_img = UUID.randomUUID().toString();
	}
	
	// saveFolder는 serviceimple에서 생성해준다. 
	public void saveFile(File saveFolder) throws IllegalStateException, IOException {
		if(prod_image==null) {
			return;
		}
		
		File savefile = new File(saveFolder, prod_img);
		// 파일 선택이 됨 > 파일 저장 
		prod_image.transferTo(savefile);
		// transferTo()라는 메소드를 사용해서 원하는 위치에 파일을 저장시킨다. 
		
	}
	
}
