package foi.air.szokpt.accountmng.controllers;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isAuthenticated = authenticationService.authenticate(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.status(200).body("Authentication successful");
        } else {
            return ResponseEntity.status(401).body("Wrong credentials");
        }
    }

}
