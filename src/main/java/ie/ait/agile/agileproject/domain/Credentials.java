package ie.ait.agile.agileproject.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Credentials {

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
    @Size(min = 4, max = 7, message
            = "BadgeNo should be between 4 to 7")
    private String badgeNo;

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

    public String getBadgeNo() {
        return badgeNo;
    }

    public void setBadgeNo(String badgeNo) {
        this.badgeNo = badgeNo;
    }
}

