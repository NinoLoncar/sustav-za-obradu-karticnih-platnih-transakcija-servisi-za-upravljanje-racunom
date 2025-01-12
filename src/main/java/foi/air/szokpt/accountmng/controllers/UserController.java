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

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final Authorizer authorizer;

    @Autowired
    public UserController(UserService userService, Authorizer authorizer) {
        this.userService = userService;
        this.authorizer = authorizer;
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<User>> getUsers(
            @RequestHeader("Authorization") String authorizationHeader) {
        authorizer.authorizeAdmin(authorizationHeader);
        List<User> users = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.successWithData("Users successfully fetched", users));
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

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(
            @PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader) {
        authorizer.authorizeAdmin(authorizationHeader);
        User user = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.successWithData("User successfully fetched", user));
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
