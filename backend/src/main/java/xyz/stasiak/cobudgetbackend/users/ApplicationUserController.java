package xyz.stasiak.cobudgetbackend.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import xyz.stasiak.cobudgetbackend.users.config.UserConfiguration;
import xyz.stasiak.cobudgetbackend.validation.ValidationExceptionProcessing;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/user")
@ValidationExceptionProcessing
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
        if (userRepository.existsByEmail(applicationUser.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exists");
        }
        ApplicationUser user = userRepository.save(applicationUser.toApplicationUser(bCryptPasswordEncoder));
        return ResponseEntity.ok(new ApplicationUserReadModel(user));
    }

    @GetMapping("/config")
    public ResponseEntity<UserConfiguration> getUserConfiguration(Principal principal) {
        var user = userRepository.findByEmail(principal.getName());
        if (user.isEmpty()) {
            return ResponseEntity.notFound()
                                 .build();
        }
        return ResponseEntity.ok(user.get()
                                     .getUserConfiguration());
    }

    @PostMapping("/config")
    public ResponseEntity<?> setUserConfiguration(Principal principal,
                                                  @RequestBody UserConfiguration userConfiguration) {
        var user = userRepository.findByEmail(principal.getName())
                                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.updateConfiguration(userConfiguration);
        userRepository.save(user);
        return ResponseEntity.ok()
                             .build();
    }

    //TODO add method to get information about user
}
