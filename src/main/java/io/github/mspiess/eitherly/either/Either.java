package io.github.mspiess.eitherly.either;

import java.util.function.Function;

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

    /**
     * If a left value is present, returns a {@code Either} describing
     * the result of applying the given mapping function to the value.
     * Otherwise, returns an {@code Either} with the unmodified right value.
     * @param mapper the mapping function to apply to a left value, if present
     * @return a {@code Either} describing the result of applying a mapping
     *         function to the left value of this {@code Either}.
     * @param <T> The type of the value returned from the mapping function
     * @throws NullPointerException if the mapping function is {@code null}
     */
    <T> Either<T, R> mapLeft(Function<? super L,? extends T> mapper);

    /**
     * If a right value is present, returns a {@code Either} describing
     * the result of applying the given mapping function to the value.
     * Otherwise, returns an {@code Either} with the unmodified right value.
     * @param mapper the mapping function to apply to a right value, if present
     * @return a {@code Either} describing the result of applying a mapping
     *         function to the right value of this {@code Either}.
     * @param <T> The type of the value returned from the mapping function
     * @throws NullPointerException if the mapping function is {@code null}
     */
    <T> Either<L, T> mapRight(Function<? super R, ? extends T> mapper);
}
