package gr.codehub.teamOne.representation;

import gr.codehub.teamOne.security.AccessRole;
import lombok.Data;

@Data
public class UsersSearchDTO {
    private Integer amka;
    private AccessRole role;
}
