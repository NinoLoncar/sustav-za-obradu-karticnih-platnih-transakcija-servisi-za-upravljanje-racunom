package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public Boolean verifyToken(String token) {
        return jwtUtil.verifyToken(token);
    }

    public Boolean checkAdminRole(String token) {
        String userRole = jwtUtil.getRoleName((token));
        return userRole.equals("admin");
    }
}
