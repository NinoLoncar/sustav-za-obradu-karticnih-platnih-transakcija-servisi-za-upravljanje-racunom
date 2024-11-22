package foi.air.szokpt.accountmng.security.hashing;

public interface Hasher {
    String hashText(String text);
    Boolean verifyHash(String plainText, String hash);
}
