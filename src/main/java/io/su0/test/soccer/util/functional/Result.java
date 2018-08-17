package io.su0.test.soccer.util.functional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Result<R, E> {

    private final R result;
    private final E error;

    private Result(R result, E error) {
        this.result = result;
        this.error = error;
    }

    public boolean isOk() {
        return Objects.isNull(error);
    }

    public R get() {
        return result;
    }

    public R getOrThrow() {
        if (isOk()) {
            return result;
        }
        else {
            if (error instanceof RuntimeException) {
                throw (RuntimeException) error;
            }
            else if (error instanceof Error) {
                throw (Error) error;
            }
            else if (error instanceof Exception) {
                throw new RuntimeException((Exception) error);
            }
            else {
                throw new RuntimeException(String.valueOf(error));
            }
        }
    }

    public E getError() {
        return error;
    }

    public <R1> Result<R1, E> map(Function<R, R1> converter) {
        if (isOk()) {
            return ok(converter.apply(result));
        }
        else {
            return error(error);
        }
    }

    public <R1> Result<R1, E> flatMap(Function<R, Result<R1, E>> converter) {
        if (isOk()) {
            return converter.apply(result);
        }
        else {
            return Result.error(error);
        }
    }

    public static <R, E> Result<R, E> ok(R result) {
        return new Result<>(result, null);
    }

    public static <R, E> Result<R, E> error(E error) {
        return new Result<>(null, error);
    }

    public static <R, E> Result<R, E> error(Supplier<E> errorSupplier) {
        return error(errorSupplier.get());
    }

    public static <E> Result<Void, E> ok() {
        return new Result<>(null, null);
    }

    public static <R, E> Result<R, E> fromOptional(Optional<R> optional, Supplier<E> errorSupplier) {
        return optional
                .<Result<R, E>>map(Result::ok)
                .orElseGet(() -> Result.<R, E>error(errorSupplier.get()));
    }
}
