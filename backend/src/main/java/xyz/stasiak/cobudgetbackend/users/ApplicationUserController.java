package xyz.stasiak.cobudgetbackend.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ApplicationUserController {

    private final ApplicationUserRepository userRepository;

    public ApplicationUserController(ApplicationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApplicationUser> createUser(@RequestBody ApplicationUser applicationUser) {
        ApplicationUser user = userRepository.save(applicationUser);
        return ResponseEntity.ok(applicationUser);
        //TODO handle when email already exists
    }
}
