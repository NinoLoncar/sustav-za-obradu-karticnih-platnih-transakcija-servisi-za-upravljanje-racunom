package foi.air.szokpt.accountmng.controllers;

import foi.air.szokpt.accountmng.dtos.requests.LoginRequest;
import foi.air.szokpt.accountmng.dtos.respones.ApiResponse;
import foi.air.szokpt.accountmng.dtos.respones.LoginResponseData;
import foi.air.szokpt.accountmng.dtos.respones.TokenValidationResponse;
import foi.air.szokpt.accountmng.services.AuthenticationService;
import foi.air.szokpt.accountmng.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseData>> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseUtil.successWithData("Successful login",
                        new LoginResponseData(token)));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<ApiResponse<TokenValidationResponse>> validateToken(
            @RequestHeader("Authorization") String authorizationHeader) {

        TokenValidationResponse validationResponse = authenticationService.validateToken(authorizationHeader);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.successWithData("Token is valid", validationResponse));
    }
}
