package io.github.mspiess.eitherly.either;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Fail.fail;

class EitherTest {

    @Test
    void leftMatchesLeft() {
        Either<String, Integer> either = Either.left("some string");
        switch (either) {
            case Left<String, ?> (String value) -> assertThat(value).isEqualTo("some string");
            case Right<?, Integer> (Integer ignored) -> fail("expected Left case");
        }
    }

    @Test
    void rightMatchesRight() {
        Either<Integer, String> either = Either.right("some value");
        switch (either) {
            case Left<Integer, ?> (Integer ignored) -> fail("expected right case");
            case Right<?, String> (String value) -> assertThat(value).isEqualTo("some value");
        }
    }

    @Test
    void mapLeft_left() {
        Either<String, Integer> left = Either.left("some value");

        var either = left.mapLeft(String::toUpperCase);
        assertThatThrownBy(() -> left.mapLeft(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(either).isExactlyInstanceOf(Left.class);
        Left<String, Integer> mappedLeft = (Left<String, Integer>) either;
        assertThat(mappedLeft.value()).isEqualTo("SOME VALUE");
    }

    @Test
    void mapLeft_right() {
        Either<Integer, String> right = Either.right("some value");

        var either = right.mapLeft(value -> {
            fail("MapLeft mapper function called on a right");
            return value;
        });
        assertThatThrownBy(() -> right.mapLeft(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(either).isExactlyInstanceOf(Right.class);
        Right<Integer, String> mappedRight = (Right<Integer, String>) either;
        assertThat(mappedRight.value()).isEqualTo("some value");
    }

    @Test
    void mapRight_right() {
        Either<Integer, String> right = Either.right("some value");

        var either = right.mapRight(String::toUpperCase);
        assertThatThrownBy(() -> right.mapRight(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(either).isExactlyInstanceOf(Right.class);
        Right<Integer, String> mappedRight = (Right<Integer, String>) either;
        assertThat(mappedRight.value()).isEqualTo("SOME VALUE");
    }

    @Test
    void mapRight_left() {
        Either<String, Integer> left = Either.left("some value");

        var either = left.mapRight(value -> {
            fail("MapLeft mapper function called on a right");
            return value;
        });
        assertThatThrownBy(() -> left.mapRight(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(either).isExactlyInstanceOf(Left.class);
        Left<String, Integer> mappedRight = (Left<String, Integer>) either;
        assertThat(mappedRight.value()).isEqualTo("some value");
    }
}
