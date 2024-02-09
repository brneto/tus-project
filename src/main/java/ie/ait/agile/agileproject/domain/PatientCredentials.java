package ie.ait.agile.agileproject.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PatientCredentials {
    @NotNull
    @Size(min = 5, max = 15, message
            = "Username should be between 5 to 15")
    private String username;

    @NotNull
    @Size(min = 5, max = 15, message
            = "password should be between 5 to 15")
    private String password;

    @NotNull
    @Size(min = 2, max = 17, message
            = "name should be between 2 to 17")
    private String name;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    private long emergencyId;


    @NotNull
    @Size(min = 5, max = 15, message
            = "Username should be between 5 to 15")
    private String gpUsername;


    public String getGpUsername() {
        return gpUsername;
    }

    public void setGpUsername(String gpUsername) {
        this.gpUsername = gpUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmergencyId(long emergencyId) {
        this.emergencyId = emergencyId;
    }

    public long getEmergencyId() {
        return emergencyId;
    }


}
