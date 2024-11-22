package foi.air.szokpt.accountmng.util.hashing;

public interface Hasher {
    String hashText(String text);
    Boolean verifyHash(String plainText, String hash);
}
