package gr.codehub.teamOne.security;

public enum AccessRole {

    ROLE_NA("n/a"),
    ROLE_ADMIN("admin"),
    ROLE_OWNER("owner"),
    ROLE_USER("user");

    private final String roleName;

    AccessRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static AccessRole getRoleValue(String roleParameter) {
        for (AccessRole accessRole : AccessRole.values()) {
            if (roleParameter.equals(accessRole.getRoleName())) {
                return accessRole;
            }
        }
        return ROLE_NA;
    }
}
