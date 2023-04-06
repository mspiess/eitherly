package io.github.mspiess.eitherly.result;

import java.util.Optional;
import java.util.function.Function;

public sealed interface Result<S, F> permits Success, Failure {
    static <S, F> Result<S, F> success(S value) {
        return new Success<>(value);
    }

    static <S, F> Result<S, F> failure(F failure) {
        return new Failure<>(failure);
    }

    <T> Result<T, F> map(Function<? super S,? extends T> mapper);

    <T> Result<S, T> mapFailure(Function<? super F, ? extends T> mapper);

    Optional<S> toOptional();
}
