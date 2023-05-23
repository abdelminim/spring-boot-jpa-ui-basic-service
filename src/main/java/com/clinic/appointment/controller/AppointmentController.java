package com.clinic.appointment.controller;

import java.sql.Date;

import com.clinic.appointment.model.AppointmentJsonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.appointment.dto.CommonResponseDto;
import com.clinic.appointment.entity.Appointment;
import com.clinic.appointment.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(path = "/AppointmentService/api/v1/auth/")
//@RequestMapping(path = "/cityApi/", consumes = "application/json", produces = "application/json")
public class AppointmentController {
	private static final Logger log = LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("getAllAppointments")
	public ResponseEntity<CommonResponseDto> getAllAppointments() {
		log.info("Start getAllAppointments");
		CommonResponseDto commonResponseDto = appointmentService.getAllAppointments();
		System.out.println("data : "+commonResponseDto.getData());
		log.info("End getAllAppointments");
		return ResponseEntity.ok(commonResponseDto);
	}

	@GetMapping("getTodayAppointments")
	public ResponseEntity<CommonResponseDto> getTodayAppointments() {
		log.info("Start getTodayAppointments");
		CommonResponseDto commonResponseDto = appointmentService.getTodayAppointments();
		System.out.println("data : "+commonResponseDto.getData());
		log.info("End getTodayAppointments");
		return ResponseEntity.ok(commonResponseDto);
	}
	@PostMapping("appointment")
	public ResponseEntity<CommonResponseDto> addAppointment(@RequestBody AppointmentJsonRequest appointment) {
		log.info("Start addAppointment");

		CommonResponseDto commonResponseDto = appointmentService.saveAppointment(appointment);
		log.info("End addAppointment");
		return ResponseEntity.ok(commonResponseDto);
	}
	@PutMapping("cancelAppointment")
	public ResponseEntity<CommonResponseDto> updateAppointment(@RequestBody Appointment appointment) {
		log.info("Start updateAppointment");
		CommonResponseDto commonResponseDto = appointmentService.updateAppointment(appointment);
		log.info("End updateAppointment");
		return ResponseEntity.ok(commonResponseDto);
	}

	@GetMapping("dayAppoientments")
	public ResponseEntity<CommonResponseDto> getDayAppointments(@RequestParam Date date) {
		log.info("Start getDayAppointments");
		CommonResponseDto commonResponseDto = appointmentService.getDayAppointments(date);
		log.info("End getDayAppointments");
		return ResponseEntity.ok(commonResponseDto);
	}

	@GetMapping("patientApoientment")
	public ResponseEntity<CommonResponseDto> getPatientAppointment(@RequestParam String patientName) {
		log.info("Start getPatientAppointment");
		CommonResponseDto commonResponseDto = appointmentService.getPatientAppointment(patientName);
		log.info("End getPatientAppointment");
		return ResponseEntity.ok(commonResponseDto);
	}


	@GetMapping("patientHistory")
	public ResponseEntity<CommonResponseDto> getPatientHistory(@RequestParam String patientName) {
		log.info("Start getPatientHistory");
		CommonResponseDto commonResponseDto = appointmentService.getPatientHistory(patientName);
		log.info("End getPatientHistory");
		return ResponseEntity.ok(commonResponseDto);
	}


}

