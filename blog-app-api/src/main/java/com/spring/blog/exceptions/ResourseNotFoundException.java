package com.spring.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException {
	
	String resouceName;
	String filedName;
	long fieldValue;
	
	public ResourseNotFoundException(String resouceName, String filedName, long fieldValue) {
		super(String.format("%s is not found with name  %s: %s", resouceName, filedName, fieldValue));
		this.resouceName = resouceName;
		this.filedName = filedName;
		this.fieldValue = fieldValue;
	}

}
