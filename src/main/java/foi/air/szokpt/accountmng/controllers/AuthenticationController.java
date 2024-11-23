package foi.air.szokpt.accountmng.controllers;

import foi.air.szokpt.accountmng.dtos.requests.LoginRequest;
import foi.air.szokpt.accountmng.dtos.respones.ApiResponse;
import foi.air.szokpt.accountmng.dtos.respones.LoginResponse;
import foi.air.szokpt.accountmng.exceptions.AuthenticationException;
import foi.air.szokpt.accountmng.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        try{
            String token = authenticationService.authenticate(loginRequest.getUsername(),loginRequest.getPassword());
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse("Successfull login", token));
        }
        catch(AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Invalid credentials"));
        }
    }
}
