package com.clinic.appointment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCodes {
	NOT_FOUNDED_APPOINTMENT("1", "Not Founded Appointment"),
	REQUIRED_DATE("2", "Required appointment date"),
	REQUIRED_PATIENT_NAME("3", "Required patient name"),
	FAILD_TO_ADD_APPOINTMENT("4", "Failed To Add Appointment");

	@Getter
	public final String message;

	@Getter
	public final String code;


	public static String getErrorMessage(String id) {
	for (ErrorCodes e : values()) {
		if (e.code .equals(id))
			return e.getMessage();
	}
	return null;
}
	
	
}
