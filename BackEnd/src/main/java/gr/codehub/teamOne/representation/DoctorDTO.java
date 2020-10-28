package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Enums.Gender;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.security.AccessRole;
import lombok.Data;

import java.util.Date;

@Data
public class DoctorDTO {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private Integer amka;
    private String mobile_phone_number;
    private String phone_number;
    private String address;
    private AccessRole accountType;

    public static Users getDoctor(DoctorDTO doctorDTO){

        Users doctor=new Users();
        doctor.setFirst_name(doctorDTO.getFirst_name());
        doctor.setLast_name(doctorDTO.getLast_name());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPassword(doctorDTO.getPassword());
        doctor.setAccountType(doctorDTO.getAccountType());
        doctor.setAmka(doctorDTO.getAmka());
        doctor.setMobile_phone_number(doctorDTO.getMobile_phone_number());
        doctor.setPhone_number(doctorDTO.getPhone_number());
        doctor.setAddress(doctorDTO.getAddress());

        return doctor;
    }

    public static DoctorDTO getDoctorDTO(Users doctor) {

        DoctorDTO doctorDTO=new DoctorDTO();
        doctorDTO.setFirst_name(doctor.getFirst_name());
        doctorDTO.setLast_name(doctor.getLast_name());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setAccountType(doctor.getAccountType());
        doctorDTO.setAmka(doctor.getAmka());
        doctorDTO.setMobile_phone_number(doctor.getMobile_phone_number());
        doctorDTO.setPhone_number(doctor.getPhone_number());
        doctorDTO.setAddress(doctor.getAddress());

        return doctorDTO;




    }
}
