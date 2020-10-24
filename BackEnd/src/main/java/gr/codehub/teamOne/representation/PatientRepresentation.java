package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Gender;
import gr.codehub.teamOne.model.PatientMeasuraments;
import gr.codehub.teamOne.model.Patients;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Date;

@Data
public class PatientRepresentation {
    private String name;
    private String email;
    private String address;

    private Date dob;

    private Gender gender;
    private String uri;



    static public Patients getPatients(PatientRepresentation patientRepresentation){
        Patients patient= new Patients();

        patient.setName(patientRepresentation.getName());
        patient.setAddress(patientRepresentation.getAddress());
        patient.setEmail(patientRepresentation.getEmail());
        patient.setDob(patientRepresentation.getDob());
        patient.setGender(patientRepresentation.getGender());

        return patient;
    }

    static public PatientRepresentation getPatientRepresentation(Patients patient){
        PatientRepresentation patientRepresentation = new PatientRepresentation();


        patientRepresentation.setName(patient.getName());
        patientRepresentation.setAddress(patient.getAddress());
        patientRepresentation.setEmail(patient.getEmail());
        patientRepresentation.setDob(patient.getDob());
        patientRepresentation.setGender(patient.getGender());
        patientRepresentation.setUri("http://localhost:9000/app/patient/"+patient.getAmka());
        return patientRepresentation;
    }
}
