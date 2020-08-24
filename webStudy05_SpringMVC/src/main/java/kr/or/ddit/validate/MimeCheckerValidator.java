package kr.or.ddit.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.stereotype.MimeChecker;

public class MimeCheckerValidator implements ConstraintValidator<MimeChecker, MultipartFile>{

	private String contentType; // ex) image/jpeg, application/* , video/*
	
	@Override
	public void initialize(MimeChecker constraintAnnotation) {
		contentType = constraintAnnotation.contentType();
		contentType = contentType.replace("*", ".+");		
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

		if(value==null) {
			return true;
		}
		
		String filename = value.getOriginalFilename();
		if(filename==null || filename.isEmpty()) {
			return true;
		}
		String fileMime = value.getContentType();
		Pattern pattern = Pattern.compile(contentType);
		Matcher matcher = pattern.matcher(fileMime);
		
		return matcher.find();
	}
	

}
