package xyz.stasiak.cobudgetbackend.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;

@Document(collection = "Users")
public class ApplicationUser {

    @Id
    private String id;

    @Email
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;

    //TODO make constructor package-private
    //TODO write method for constructing user object with default config

    ApplicationUser() {
    }

    public ApplicationUser(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
