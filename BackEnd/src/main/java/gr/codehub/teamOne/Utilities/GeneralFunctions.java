package gr.codehub.teamOne.Utilities;

import gr.codehub.teamOne.security.AccessRole;

import java.util.ArrayList;
import java.util.List;

public class GeneralFunctions {

    /**
     * To avoid multiple creation of List only to set roles that have access on method
     *
     * @param patient  if its true means that account with roleType User have access to method
     * @param doctor if its true means that account with roleType Owner have access to method
     * @param admin if its true means that account with roleType Admin have access to method
     * @return A list of string with tags of roles that have access on method
     */
    public static List<String> rolesWithAccess(boolean patient, boolean doctor, boolean admin) {
        List<String> tempListWithRoles = new ArrayList<>();
        if (doctor) {
            tempListWithRoles.add(AccessRole.ROLE_DOCTOR.getRoleName());
        }
        if (patient) {
            tempListWithRoles.add(AccessRole.ROLE_PATIENT.getRoleName());
        }
        if (admin) {
            tempListWithRoles.add(AccessRole.ROLE_ADMIN.getRoleName());
        }
        return tempListWithRoles;
    }
}
