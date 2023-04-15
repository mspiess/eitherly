# Eitherly

What if `java.util.Optional`'s empty could be anything?

## Motivation

Exceptions should be reserved for exceptional situations.
Relying on exceptions for control flow is far from ideal, but often necessary for a lack of alternatives in the Java language.
New Java features that will *hopefully* land in JDK 21 enable one such alternative.

## Example

```Java
public Result<Balance, String> withdraw(Amount amount) {
    if(amount.isLargerThan(MAXIMUM_WITHDRAWAL_AMOUNT)) {
        return Result.failure("You cannot withdraw more than " + MAXIMUM_WITHDRAWAL_AMOUNT + " at once.");
    }
    if(amount.isLargerThan(this.balance)) {
        return Result.failure("Insufficient funds.");
    }
    this.balance = this.balance.subtract(amount);
    return Result.success(this.balance);
}

// Caller
String getDisplayMessage() {
    var amount = Amount.of(50);
    Result<String, String> withdrawalResult = account.withdraw(amount)
        .map((newBalance) -> "Your new Balance is " + newBalance + ".");
    return switch (result) {
        case Success<String, ?>(String message) -> {
            emitCash(amount);
            yield message;
        }
        case Failure<?, String>(String message) -> message;
    }
}
```

## Stability

This library assumes features that are in preview as of JDK 20, so it is not recommended for use in production.
