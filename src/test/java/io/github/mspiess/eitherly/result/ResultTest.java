package io.github.mspiess.eitherly.result;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Fail.fail;

public class ResultTest {
    @Test
    void success_matchesSuccess() {
        String successValue = "some value";
        Result<String, Integer> result = Result.success(successValue);

        switch (result) {
            case Success<String, ?>(String value) -> assertThat(value).isEqualTo(successValue);
            case Failure<?, Integer>(Integer ignored) -> fail("expected a success");
        }
    }

    @Test
    void failure_matchesFailure() {
        String expectedError = "some failure";
        Result<Integer, String> result = Result.failure(expectedError);

        switch (result) {
            case Success<Integer, ?>(Integer ignored) -> fail("expected a failure");
            case Failure<?, String>(String error) -> assertThat(error).isEqualTo(expectedError);
        }
    }

    @Test
    void map_success() {
        Result<String, Integer> success = Result.success("some success");

        var result = success.map(String::toUpperCase);
        assertThatThrownBy(() -> success.map(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(result).isExactlyInstanceOf(Success.class);
        Success<String, Integer> mappedSuccess = (Success<String, Integer>) result;
        assertThat(mappedSuccess.value()).isEqualTo("SOME SUCCESS");
    }

    @Test
    void map_failure() {
        Result<Integer, String> failure = Result.failure("some failure");

        var result = failure.map(value -> {
            fail("Mapper function called on a failure");
            return value;
        });
        assertThatThrownBy(() -> failure.map(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(result).isExactlyInstanceOf(Failure.class);
        Failure<Integer, String> mappedFailure = (Failure<Integer, String>) result;
        assertThat(mappedFailure.failure()).isEqualTo("some failure");
    }

    @Test
    void mapFailure_failure() {
        Result<Integer, String> failure = Result.failure("some failure");

        var result = failure.mapFailure(String::toUpperCase);
        assertThatThrownBy(() -> failure.mapFailure(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(result).isExactlyInstanceOf(Failure.class);
        Failure<Integer, String> mappedFailure = (Failure<Integer, String>) result;
        assertThat(mappedFailure.failure()).isEqualTo("SOME FAILURE");
    }

    @Test
    void mapFailure_success() {
        Result<String, Integer> success = Result.success("some success");

        var result = success.mapFailure(value -> {
            fail("Mapper function called on a failure");
            return value;
        });
        assertThatThrownBy(() -> success.mapFailure(null))
                .isExactlyInstanceOf(NullPointerException.class);

        assertThat(result).isExactlyInstanceOf(Success.class);
        Success<String, Integer> mappedFailure = (Success<String, Integer>) result;
        assertThat(mappedFailure.value()).isEqualTo("some success");
    }

    @Test
    void toOptional() {
        Result<String, Integer> success = Result.success("Some success");
        Result<Integer, String> failure = Result.failure("Some failure");
        Result<String, Integer> nullSuccess = Result.success(null);

        assertThat(success.toOptional()).hasValue("Some success");
        assertThat(failure.toOptional()).isEmpty();
        assertThatThrownBy(nullSuccess::toOptional).isExactlyInstanceOf(NullPointerException.class);
    }
}
