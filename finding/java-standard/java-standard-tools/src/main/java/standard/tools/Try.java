package standard.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Function;

@FunctionalInterface
public interface Try<T, R, E extends Throwable> {

    Logger log = LogManager.getLogger(Try.class);

    R apply(T t) throws E;

    static <T, R, E extends Throwable> Function<T, R> of(final Try<T, R, E> fn) {
        return t -> {
            try {
                return fn.apply(t);
            } catch (Throwable e) {
                log.error("Action Fun Err Param {}", t, e);
                throw new RuntimeException(e);
            }
        };
    }
}