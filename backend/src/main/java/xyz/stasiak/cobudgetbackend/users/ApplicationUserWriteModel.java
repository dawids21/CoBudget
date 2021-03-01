package xyz.stasiak.cobudgetbackend.users;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;

public class ApplicationUserWriteModel {

    @Email
    private String email;
    private String password;
    private String name;

    public ApplicationUserWriteModel(@Email String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
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

    public ApplicationUser toApplicationUser(PasswordEncoder passwordEncoder) {
        return new ApplicationUser(email, passwordEncoder.encode(password), name);
    }
}
