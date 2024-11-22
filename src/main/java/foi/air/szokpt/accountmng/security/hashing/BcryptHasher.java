package foi.air.szokpt.accountmng.security.hashing;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptHasher implements Hasher{

    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    public BcryptHasher() {
        this.bcryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hashText(String text) {
        return bcryptPasswordEncoder.encode(text);
    }
}
