package io.github.mspiess.eitherly.result;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public record Success<S, E>(S value) implements Result<S, E> {
    @Override
    public <T> Result<T, E> map(Function<? super S, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        return new Success<>(mapper.apply(value()));
    }

    @Override
    public <T> Result<S, T> mapFailure(Function<? super E, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        // noinspection unchecked
        return (Result<S, T>) this;
    }

    @Override
    public Optional<S> toOptional() {
        return Optional.of(value());
    }
}
