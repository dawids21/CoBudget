package xyz.stasiak.cobudgetbackend.users;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<ApplicationUserReadModel> createUser(
             @Valid @RequestBody ApplicationUserWriteModel applicationUser) {
        ApplicationUser user = userRepository.save(applicationUser.toApplicationUser(bCryptPasswordEncoder));
        return ResponseEntity.ok(new ApplicationUserReadModel(user));
        //TODO handle when email already exists
    }
}
