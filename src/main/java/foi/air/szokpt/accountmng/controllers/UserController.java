package foi.air.szokpt.accountmng.controllers;

import foi.air.szokpt.accountmng.dtos.respones.ApiResponse;
import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.services.UserService;
import foi.air.szokpt.accountmng.util.ApiResponseUtil;
import foi.air.szokpt.accountmng.util.Authorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    private final Authorizer authorizer;

    @Autowired
    public UserController(UserService userService, Authorizer authorizer) {
        this.userService = userService;
        this.authorizer = authorizer;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody User user) {
        authorizer.authorizeAdmin(authorizationHeader);
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.success("User successfully registered"));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ApiResponse<Void>> updateUser(
            @PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody User newUserData) {
        authorizer.authorizeAdmin(authorizationHeader);
        userService.updateUser(id, newUserData);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.success("User successfully updated"));
    }
}
