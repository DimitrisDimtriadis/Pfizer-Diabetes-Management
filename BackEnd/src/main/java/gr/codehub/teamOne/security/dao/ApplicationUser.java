package gr.codehub.teamOne.security.dao;

import gr.codehub.teamOne.security.AccessRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser {

    private String username;
    private String password;
    private AccessRole accessRole;
}
