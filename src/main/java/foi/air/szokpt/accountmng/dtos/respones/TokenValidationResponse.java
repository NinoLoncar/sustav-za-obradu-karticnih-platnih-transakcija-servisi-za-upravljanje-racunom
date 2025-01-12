package foi.air.szokpt.accountmng.dtos.respones;

public class TokenValidationResponse {
    private String role;

    public TokenValidationResponse() {
    }

    public TokenValidationResponse(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
