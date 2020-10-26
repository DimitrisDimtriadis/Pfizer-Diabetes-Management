package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.model.Users;
import gr.codehub.teamOne.security.AccessRole;
import lombok.Data;

@Data
public class UsersDTO {

    private String first_name;
    private String last_name;
    private String password;
    private AccessRole accountType;
    private Integer amka;
    private String uri;


    public static Users getUsers(UsersDTO usersDTO){
        Users users = new Users();
        users.setAccountType(usersDTO.getAccountType());
        users.setFirst_name(usersDTO.getFirst_name());
        users.setLast_name(usersDTO.getLast_name());
        users.setAmka(usersDTO.getAmka());
        users.setPassword(usersDTO.getPassword());
        return users;
    }

    public static UsersDTO getUsersDTO(Users users){
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setAccountType(users.getAccountType());
        usersDTO.setFirst_name(users.getFirst_name());
        usersDTO.setLast_name(users.getLast_name());
        usersDTO.setAmka(users.getAmka());
        usersDTO.setPassword(users.getPassword());
        usersDTO.setUri("Id of object " + users.getId());

        return usersDTO;
    }
}
