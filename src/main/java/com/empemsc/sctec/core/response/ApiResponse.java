package com.empemsc.sctec.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Envelope padrão de respostas da API")
public record ApiResponse<T>(
    @Schema(description = "Indica se a requisição foi processada com sucesso", example = "true")
    boolean success,
    @Schema(description = "Os dados retornados pela requisição (em caso de sucesso)")
    T data,
    @Schema(description = "Lista de mensagens de erro (em caso de falha)", example = "[\"O campo nome é obrigatório\"]")
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