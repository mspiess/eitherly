package io.github.mspiess.eitherly.result;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

public class ResultTest {
    @Test
    void successMatchesSuccess() {
        String successValue = "some value";
        Result<String, Integer> result = Result.success(successValue);

        switch (result) {
            case Success<String, ?>(String value) -> assertThat(value).isEqualTo(successValue);
            case Failure<?, Integer>(Integer ignored) -> fail("expected a success");
        }
    }

    @Test
    void failureMatchesFailure() {
        String expectedError = "some failure";
        Result<Integer, String> result = Result.failure(expectedError);

        switch (result) {
            case Success<Integer, ?>(Integer ignored) -> fail("expected a failure");
            case Failure<?, String>(String error) -> assertThat(error).isEqualTo(expectedError);
        }
    }

    @Test
    void mapSuccess() {
        Result<String, Integer> success = Result.success("some success");

        var result = success.map(String::toUpperCase);

        assertThat(result).isExactlyInstanceOf(Success.class);
        Success<String, Integer> mappedSuccess = (Success<String, Integer>) result;
        assertThat(mappedSuccess.value()).isEqualTo("SOME SUCCESS");
    }

    @Test
    void mapFailure() {
        Result<Integer, String> failure = Result.failure("some failure");

        var result = failure.map(value -> {
            fail("Mapper function called on a failure");
            return value;
        });

        assertThat(result).isExactlyInstanceOf(Failure.class);
        Failure<Integer, String> mappedFailure = (Failure<Integer, String>) result;
        assertThat(mappedFailure.failure()).isEqualTo("some failure");
    }

}
