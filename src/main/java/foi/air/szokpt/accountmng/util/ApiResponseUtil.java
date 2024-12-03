package foi.air.szokpt.accountmng.util;

import foi.air.szokpt.accountmng.dtos.respones.ApiResponse;

public class ApiResponseUtil {
    public static <T> ApiResponse<T> successWithData(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, message, null);
    }
}
