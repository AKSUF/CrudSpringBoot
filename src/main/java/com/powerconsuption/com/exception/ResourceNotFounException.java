package com.powerconsuption.com.exception;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResourceNotFounException extends RuntimeException {
	String resourceName;
	String resourceFieldName;
	long fieldValue;
	
	public ResourceNotFounException(String resourceName, String resourceFieldName, long fieldValue) {
		super("%s not found with %s:%1");
		this.resourceName = resourceName;
		this.resourceFieldName = resourceFieldName;
		this.fieldValue = fieldValue;
	}
}
