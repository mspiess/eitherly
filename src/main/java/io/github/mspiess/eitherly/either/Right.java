package io.github.mspiess.eitherly.either;

public record Right<L, R>(R value) implements Either<L, R> {
}
