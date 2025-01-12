package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.dtos.respones.TokenValidationResponse;
import foi.air.szokpt.accountmng.exceptions.JwtException;
import foi.air.szokpt.accountmng.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {
    private final JwtUtil jwtUtil;

    public TokenValidationService(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    public TokenValidationResponse validateToken(String authorizationHeader) {
        String token = jwtUtil.extractToken(authorizationHeader);
        boolean isValid = jwtUtil.verifyToken(token);

        if (!isValid)
            throw new JwtException("Invalid token");

        String role = jwtUtil.getRoleName(token);
        return new TokenValidationResponse(role);
    }
}
