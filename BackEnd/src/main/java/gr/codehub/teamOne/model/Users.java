package gr.codehub.teamOne.model;

import gr.codehub.teamOne.model.Enums.Gender;
import gr.codehub.teamOne.security.AccessRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String phone_number;
    private AccessRole accountType;

    private long AMKA;

    private Gender gender;
    private Date registration_date;
}