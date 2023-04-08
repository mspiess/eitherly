package io.github.mspiess.eitherly.result;

import java.util.Optional;
import java.util.function.Function;

/**
 * A container object which may either contain success value or a failure value.
 * Can be considered a specialization of {@link io.github.mspiess.eitherly.either.Either}.
 *
 * @param <S> Type of the success value
 * @param <F> Type of the failure value
 */
public sealed interface Result<S, F> permits Success, Failure {
    /**
     * Returns a {@code Result} instance with the described success value.
     *
     * @param value The success value to describe
     * @return a {@code Result} with the success value present
     * @param <S> Type of the success value
     * @param <F> Type of the failure value
     */
    static <S, F> Result<S, F> success(S value) {
        return new Success<>(value);
    }

    /**
     * Returns a {@code Result} instance with the described failure value.
     *
     * @param failure The failure value to describe
     * @return a {@code Result} with the failure value present
     * @param <S> Type of the success value
     * @param <F> Type of the failure value
     */
    static <S, F> Result<S, F> failure(F failure) {
        return new Failure<>(failure);
    }

    /**
     * If a success value is present, returns a {@code Result} describing
     * the result of applying the given mapping function to the value.
     * Otherwise, returns the unmodified failure.
     *
     * @param mapper the mapping function to apply to a success value, if present
     * @return a {@code Result} describing the result of applying a mapping
     *         function to the value of this {@code Result}, if it is a success,
     *         otherwise it returns the unmodified failure result.
     * @param <T> The type of the value returned from the mapping function
     * @throws NullPointerException if the mapping function is {@code null}
     */
    <T> Result<T, F> map(Function<? super S,? extends T> mapper);

    /**
     * If a failure value is present, returns a {@code Result} describing
     * the result of applying the given mapping function to the value.
     * Otherwise, returns the unmodified success.
     *
     * @param mapper the mapping function to apply to a failure value, if present
     * @return a {@code Result} describing the result of applying a mapping
     *         function to the value of this {@code Result}, if it is a failure,
     *         otherwise it returns the unmodified success result.
     * @param <T> The type of the value returned from the mapping function
     * @throws NullPointerException if the mapping function is {@code null}
     */
    <T> Result<S, T> mapFailure(Function<? super F, ? extends T> mapper);

    /**
     * Returns an {@link Optional} describing the success value or this result,
     * or an empty {@code Optional} if the result is a failure.
     *
     * @return an {@code Optional} describing the success value of this result,
     * or an empty {@code Optional} if the result is a failure.
     * @throws NullPointerException if the success value is null
     */
    Optional<S> toOptional();
}
