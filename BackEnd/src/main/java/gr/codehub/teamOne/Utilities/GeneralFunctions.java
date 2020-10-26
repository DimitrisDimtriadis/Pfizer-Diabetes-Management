package gr.codehub.teamOne.Utilities;

import gr.codehub.teamOne.security.AccessRole;

import java.util.ArrayList;
import java.util.List;

public class GeneralFunctions {

    /**
     * To avoid multiple creation of List only to set roles that have access on method
     *
     * @param user  if its true means that account with roleType User have access to method
     * @param owner if its true means that account with roleType Owner have access to method
     * @param admin if its true means that account with roleType Admin have access to method
     * @return A list of string with tags of roles that have access on method
     */
    public static List<String> rolesWithAccess(boolean user, boolean owner, boolean admin) {
        List<String> tempListWithRoles = new ArrayList<>();
        if (user) {
            tempListWithRoles.add(AccessRole.ROLE_USER.getRoleName());
        }
        if (owner) {
            tempListWithRoles.add(AccessRole.ROLE_OWNER.getRoleName());
        }
        if (admin) {
            tempListWithRoles.add(AccessRole.ROLE_ADMIN.getRoleName());
        }
        return tempListWithRoles;
    }
}
