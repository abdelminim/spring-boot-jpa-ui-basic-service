package com.clinic.appointment.dto;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class CommonResponseDto {
	private boolean success;
	private String message;
	private Object data;
	private Object error;
	private String code;
	
}
