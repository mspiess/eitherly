package io.github.mspiess.eitherly.result;

import java.util.function.Function;

public record Failure<S, F>(F failure) implements Result<S, F> {
    @Override
    public <T> Result<T, F> map(Function<? super S, ? extends T> mapper) {
        //noinspection unchecked
        return (Failure<T, F>) this;
    }
}
