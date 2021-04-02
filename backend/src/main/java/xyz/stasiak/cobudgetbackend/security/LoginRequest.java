package xyz.stasiak.cobudgetbackend.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class LoginRequest {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public LoginRequest(
             @NotBlank(message = "Email is mandatory") @Email(message = "Please provide valid email address") String email,
             @NotBlank(message = "Password is mandatory") String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginRequest that = (LoginRequest) o;
        return Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "LoginRequest{" + "email='" + email + '\'' + ", password='" + password + '\'' + '}';
    }
}
