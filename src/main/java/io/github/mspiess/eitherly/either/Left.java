package io.github.mspiess.eitherly.either;

public record Left<L, R>(L value) implements Either<L, R> {
}
