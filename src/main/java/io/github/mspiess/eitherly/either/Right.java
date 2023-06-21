package io.github.mspiess.eitherly.either;

import java.util.Objects;
import java.util.function.Function;

public record Right<L, R>(R value) implements Either<L, R> {
    @Override
    public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        // noinspection unchecked
        return (Either<T, R>) this;
    }

    @Override
    public <T> Either<L, T> mapRight(Function<? super R, ? extends T> mapper) {
        Objects.requireNonNull(mapper);
        return new Right<>(mapper.apply(value()));
    }
}
