package gr.codehub.teamOne.security.dao;

public enum AccessRole {
    ROLE_NA("n/a"),
    ROLE_ADMIN("Admin"),
    ROLE_DOCTOR("owner"),
    ROLE_PATIENT("user");

    private final String roleName;

    AccessRole(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static AccessRole getRoleValue(String roleParameter){
        for(AccessRole accessRole : AccessRole.values()){
            if(roleParameter.equalsIgnoreCase(accessRole.getRoleName())){
                return accessRole;
            }
        }
        return ROLE_NA;
    }
}
