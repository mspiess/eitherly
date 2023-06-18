package io.github.mspiess.eitherly.either;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

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
}
