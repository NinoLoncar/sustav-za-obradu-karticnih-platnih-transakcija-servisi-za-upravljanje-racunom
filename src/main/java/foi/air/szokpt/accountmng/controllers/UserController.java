package foi.air.szokpt.accountmng.controllers;

import foi.air.szokpt.accountmng.dtos.respones.ApiResponse;
import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody User user) {
        userService.registerUser(authorizationHeader, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("User successfully registered"));
    }
}
