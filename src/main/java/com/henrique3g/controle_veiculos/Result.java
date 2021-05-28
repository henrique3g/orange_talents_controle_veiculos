package com.henrique3g.controle_veiculos;

import java.util.Optional;
import java.util.function.Function;

public class Result<T> {
    private String error;
    private Optional<T> value;

    private Result(Optional<T> value, String error) {
        this.value = value;
        this.error = error;
    }

    public <X extends RuntimeException> T orElseThrow(Function<String, X> func) {
        return value.orElseThrow(() -> func.apply(error));
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(Optional.empty(), message);
    }

    public static <T> Result<T> ok(T value) {
        return new Result<>(Optional.of(value), null);
    }
}
