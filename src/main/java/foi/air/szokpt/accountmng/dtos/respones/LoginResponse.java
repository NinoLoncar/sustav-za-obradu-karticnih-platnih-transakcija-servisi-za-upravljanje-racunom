package foi.air.szokpt.accountmng.dtos.respones;

public class LoginResponse extends ApiResponse {
    private String token;

    public LoginResponse(String message, String token) {
        super(message);
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
