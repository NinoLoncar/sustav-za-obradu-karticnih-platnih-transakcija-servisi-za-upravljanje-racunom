package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.util.JwtUtil;

public class JwtService {
    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Boolean verifyToken(String token) {
        return jwtUtil.verifyToken(token);
    }
}
