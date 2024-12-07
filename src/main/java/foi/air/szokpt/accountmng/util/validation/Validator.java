package foi.air.szokpt.accountmng.util.validation;

public interface Validator<T> {
    void validateData(T object);
}
