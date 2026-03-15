package com.empemsc.sctec.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(
    boolean success,
    T data,
    List<String> errors
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(String errorMessage) {
        return new ApiResponse<>(false, null, Collections.singletonList(errorMessage));
    }

    public static <T> ApiResponse<T> error(List<String> errorMessages) {
        return new ApiResponse<>(false, null, errorMessages);
    }
}