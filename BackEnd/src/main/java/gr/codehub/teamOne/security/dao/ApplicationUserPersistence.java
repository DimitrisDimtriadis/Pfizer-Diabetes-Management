package gr.codehub.teamOne.security.dao;

import lombok.NoArgsConstructor;
import org.restlet.Application;
import org.restlet.Context;

import java.sql.*;
import java.util.Objects;

@NoArgsConstructor
public class ApplicationUserPersistence {

    private static ApplicationUserPersistence applicationUserPersistence = new ApplicationUserPersistence();

    public static synchronized ApplicationUserPersistence getApplicationUserPersistence(){
        return applicationUserPersistence;
    }

    public ApplicationUser findById(String username) throws SQLException{

        Context.getCurrentLogger().finer("Method findById() of ApplicationUserPersistence called.");
        Connection connection = null;

        try{
            connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from UserTable where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                ApplicationUser user = new ApplicationUser();
                user.setUsername(rs.getString("username"));
                user.setUsername(rs.getString("password"));
                user.setAccessRole(AccessRole.getRoleValue(rs.getString("role")));
                return user;
            }
            return null;
        } finally {
            releaseConnection(connection);
            Context.getCurrentLogger().finer("Method findById() of CompanyPersistence finished.");
        }
    }

    private void releaseConnection(Connection connection) throws SQLException{
        Context.getCurrentLogger().finer("Release connection: " + Objects.toString(connection));

        if(connection != null){
            connection.close();
            Context.getCurrentLogger().finer("Connection released: " + Objects.toString(connection));
        }
    }

    protected Connection getConnection() throws SQLException {
        Context.getCurrentLogger().finer("Get a fresh connection to database");
        Connection result = DriverManager.getConnection(DatabaseCredentials.URL, DatabaseCredentials.USER, DatabaseCredentials.PASSWORD);
        Context.getCurrentLogger().fine("Got a fresh connection to database");
        return result;
    }
}
