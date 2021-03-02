package xyz.stasiak.cobudgetbackend.users;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ApplicationUserController {

    private final ApplicationUserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ApplicationUserController(ApplicationUserRepository userRepository,
                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/auth")
    public ResponseEntity<?> testAuthorization() {
        return ResponseEntity.ok()
                             .build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApplicationUser> createUser(@RequestBody ApplicationUser applicationUser) {
        applicationUser.setPassword(bCryptPasswordEncoder.encode(applicationUser.getPassword()));
        ApplicationUser user = userRepository.save(applicationUser);
        return ResponseEntity.ok(applicationUser);
        //TODO handle when email already exists
    }
    //TODO add method to get information about user
}
