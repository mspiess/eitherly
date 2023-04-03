package io.github.mspiess.eitherly.result;

import java.util.Objects;
import java.util.function.Function;

public record Success<S, E>(S value) implements Result<S, E> {
    @Override
    public <T> Result<T, E> map(Function<? super S,? extends T> mapperFunction) {
        Objects.requireNonNull(mapperFunction);
        return new Success<>(mapperFunction.apply(this.value));
    }
}
