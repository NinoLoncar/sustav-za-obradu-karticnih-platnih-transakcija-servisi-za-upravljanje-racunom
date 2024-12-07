package foi.air.szokpt.accountmng.dtos.respones;

public class LoginResponseData{
    private String token;

    public LoginResponseData( String token) {
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
