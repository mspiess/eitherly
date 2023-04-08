package io.github.mspiess.eitherly.either;

/**
 * A container object which contains one exactly one value: either left or right
 *
 * @param <L> Type of the left value
 * @param <R> Type of the right value
 */
public sealed interface Either<L, R> permits Left, Right {
    /**
     * Returns an {@code Either} with the described left value
     * @param value The value to describe
     * @return an {@code Either} with the left value present
     * @param <L> Type of the left value
     * @param <R> Type of the right value
     */
    static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    /**
     * Returns an {@code Either} with the described right value
     * @param value The value to describe
     * @return an {@code Either} with the right value present
     * @param <L> Type of the left value
     * @param <R> Type of the right value
     */
    static <L,R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

}
