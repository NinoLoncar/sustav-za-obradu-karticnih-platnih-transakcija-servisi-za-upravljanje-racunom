package foi.air.szokpt.accountmng.util;

import foi.air.szokpt.accountmng.exceptions.AuthorizationException;
import foi.air.szokpt.accountmng.exceptions.JwtException;
import org.springframework.stereotype.Component;

@Component
public class Authorizer {

    private final JwtUtil jwtUtil;

    public Authorizer(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public void authorizeAdmin(String authorizationHeader) {
        String token = jwtUtil.extractToken(authorizationHeader);
        verifyToken(token);
        checkAdminRole(token);
    }

    private void verifyToken(String token) {
        if (!jwtUtil.verifyToken(token)) {
            throw new JwtException("Invalid token");
        }
    }

    private void checkAdminRole(String token) {
        String userRole = jwtUtil.getRoleName((token));
        if (!userRole.equals("admin")) {
            throw new AuthorizationException("User role is not authorized for this action");
        }
    }
}
