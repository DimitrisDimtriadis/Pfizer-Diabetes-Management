package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Enums.Gender;
import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.security.AccessRole;
import lombok.Data;

import java.util.Date;

@Data
public class UsersDTO {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private AccessRole accountType;
    private Integer amka;
    private String mobile_phone_number;
    private String phone_number;
    private String address;
    private Gender gender;
    private Date registration_date;
    private String uri;

    public static Users getUsers(UsersDTO usersDTO){

        Users users = new Users();
        users.setFirst_name(usersDTO.getFirst_name());
        users.setLast_name(usersDTO.getLast_name());
        users.setEmail(usersDTO.getEmail());
        users.setPassword(usersDTO.getPassword());
        users.setAccountType(usersDTO.getAccountType());
        users.setAmka(usersDTO.getAmka());
        users.setMobile_phone_number(usersDTO.getMobile_phone_number());
        users.setPhone_number(usersDTO.getPhone_number());
        users.setAddress(usersDTO.getAddress());
        users.setGender(usersDTO.getGender());
        users.setRegistration_date(new Date());

        return users;
    }

    public static UsersDTO getUsersDTO(Users users){

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setAccountType(users.getAccountType());
        usersDTO.setFirst_name(users.getFirst_name());
        usersDTO.setLast_name(users.getLast_name());
        usersDTO.setEmail(users.getEmail());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setAccountType(users.getAccountType());
        usersDTO.setAmka(users.getAmka());
        usersDTO.setMobile_phone_number(users.getMobile_phone_number());
        usersDTO.setPhone_number(users.getPhone_number());
        usersDTO.setAddress(users.getAddress());
        usersDTO.setGender(users.getGender());
        usersDTO.setRegistration_date(users.getRegistration_date());
        usersDTO.setUri("Id of object " + users.getId());

        return usersDTO;
    }
}