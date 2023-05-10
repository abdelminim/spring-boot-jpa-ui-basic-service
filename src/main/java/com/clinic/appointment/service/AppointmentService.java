package com.clinic.appointment.service;

import java.sql.Date;
import java.util.List;

import com.clinic.appointment.model.AppointmentJsonRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.appointment.dto.CommonResponseDto;
import com.clinic.appointment.entity.Appointment;
import com.clinic.appointment.exception.ErrorCodes;
import com.clinic.appointment.exception.MissingOrBadParameterException;
import com.clinic.appointment.repository.AppointmentRepository;
import com.clinic.appointment.util.DateUtil;
import com.clinic.appointment.util.ResponseUtil;
import com.clinic.appointment.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;



@Service
@Slf4j
public class AppointmentService {

	private static final Logger log = LoggerFactory.getLogger(AppointmentService.class);
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public CommonResponseDto saveAppointment(AppointmentJsonRequest appointment) {
		ValidationUtil.validateAppointmentDate(appointment.getAppointmentDate());
		ValidationUtil.validatePatientName(appointment.getPatientName());
		Appointment app=new Appointment();
		app.setAppointmentDate(appointment.getAppointmentDate());
		app.setPatientName(appointment.getPatientName());
		app.setCancellationReason(appointment.getCancellationReason());
		try {
			appointmentRepository.save(app);
		} catch (Exception e) {
			return ResponseUtil.buildResponse(null, ErrorCodes.FAILD_TO_ADD_APPOINTMENT.message, e,ErrorCodes.FAILD_TO_ADD_APPOINTMENT.code);
		}
		
		return ResponseUtil.buildResponse(appointment, "success", null);
	}
	
	public CommonResponseDto getAllAppointments() {
		List <Appointment> appointments = appointmentRepository.getAllAppointments();
		return ResponseUtil.buildResponse(appointments, "success", null);

	}

	public CommonResponseDto getTodayAppointments() {
		Date todayStartDate = DateUtil.getTodayStart();
		Date todayEndDate = DateUtil.getTodayEnd();
		log.info(" getTodayAppointments start : "+ todayStartDate +" , todayEndDate : "+ todayEndDate);
		List <Appointment> appointments = appointmentRepository.getAllTodayAppointments(todayStartDate,todayEndDate);
		return ResponseUtil.buildResponse(appointments, "success", null);

	}

	public CommonResponseDto getDayAppointments(Date date) {
        Date sqlDateStart = DateUtil.getDayStart(date);
        Date sqlDateEnd = DateUtil.getDayEnd(date);
		List <Appointment> appointments = appointmentRepository.getDayAppointments(sqlDateStart,sqlDateEnd);
		return ResponseUtil.buildResponse(appointments, "success", null);
	}
	public CommonResponseDto getPatientAppointment(String patientName) {
		if(patientName == null || patientName.equals("")) {
			throw new MissingOrBadParameterException(ErrorCodes.REQUIRED_PATIENT_NAME.getMessage(),
					ErrorCodes.REQUIRED_PATIENT_NAME.getCode());
		}
		Date sqlDateStart = DateUtil.getTodayStart();
        Date sqlDateEnd = DateUtil.getTodayEnd();
		List <Appointment> appointments = appointmentRepository.getPatientTodayAppointments(patientName,sqlDateStart,sqlDateEnd);
		return ResponseUtil.buildResponse(appointments, "success", null);
	}
	
	public CommonResponseDto getPatientHistory(String patientName) {
		if(patientName == null || patientName.equals("")) {
			throw new MissingOrBadParameterException(ErrorCodes.REQUIRED_PATIENT_NAME.getMessage(),
					ErrorCodes.REQUIRED_PATIENT_NAME.getCode());
		}
		List <Appointment> appointments = appointmentRepository.findByPatientName(patientName);
		return ResponseUtil.buildResponse(appointments, "success", null);
		
	}
	
	public CommonResponseDto updateAppointment(Appointment appointment) {
		Appointment existingAppointment = appointmentRepository.findById(appointment.getId().intValue()).orElse(null);
		if(existingAppointment == null) {
			log.info("Appointment not found in database");
			throw new MissingOrBadParameterException(ErrorCodes.NOT_FOUNDED_APPOINTMENT.getMessage(),
					ErrorCodes.NOT_FOUNDED_APPOINTMENT.getCode());
		}
		existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
		existingAppointment.setCancellationReason(appointment.getCancellationReason());
		existingAppointment.setPatientName(appointment.getPatientName());
		return ResponseUtil.buildResponse(appointmentRepository.save(existingAppointment), "success", null);
		
	}	

}
