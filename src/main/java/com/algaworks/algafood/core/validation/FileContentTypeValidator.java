package com.algaworks.algafood.core.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

/**
 * Classe de validação do tipo do arquivo
 * @author Leonardo
 *
 */
public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> allowedContentTypes;
	
	@Override
	public void initialize(FileContentType constraint) {
		this.allowedContentTypes = Arrays.asList(constraint.allowed());
	}
	
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		return multipartFile == null 
				|| this.allowedContentTypes.contains(multipartFile.getContentType());
	}

}