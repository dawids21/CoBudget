package xyz.stasiak.cobudgetbackend.users;

import javax.validation.constraints.Email;

public class ApplicationUserReadModel {

    @Email
    private final String email;
    private final String name;

    public ApplicationUserReadModel(ApplicationUser applicationUser) {
        email = applicationUser.getEmail();
        name = applicationUser.getName();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
