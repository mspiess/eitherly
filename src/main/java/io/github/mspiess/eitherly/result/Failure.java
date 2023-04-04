package io.github.mspiess.eitherly.result;

import java.util.Objects;
import java.util.function.Function;

public record Failure<S, F>(F failure) implements Result<S, F> {

    @Override
    public <T> Result<T, F> map(Function<? super S, ? extends T> mapper) {
        //noinspection unchecked
        return (Failure<T, F>) this;
    }

    @Override
    public <T> Result<S, T> mapFailure(Function<? super F, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        return new Failure<>(mapper.apply(this.failure()));
    }
}
