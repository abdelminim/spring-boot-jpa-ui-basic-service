package com.clinic.appointment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Data
@AllArgsConstructor
@Getter
@Setter
public class AppointmentJsonRequest {

    @JsonProperty("patientName")
    private String patientName;

    @JsonProperty("appointmentDate")
    private Date appointmentDate;

    @JsonProperty("cancellationReason")
    private String cancellationReason;

    public AppointmentJsonRequest() {
    }


    @Override
    public String toString() {
        return "JsonRequest{" + "patientName=" + patientName + "appointmentDate=" + appointmentDate + "cancellationReason=" + cancellationReason + '}';
    }
    
    

}
