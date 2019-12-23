package standard.tools;

import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * from <a href="https://javaspecialists.eu/archive/Issue275.html">https://javaspecialists.eu/archive/Issue275.html</a>
 *
 * @param <T>
 */
public interface EnhancedStream<T> extends Stream<T> {

    EnhancedStream<T> distinct(ToIntFunction<T> hashCode, BiPredicate<T, T> equals, BinaryOperator<T> merger);

    EnhancedStream<T> filter(Predicate<? super T> predicate);

    <R> EnhancedStream<R> map(Function<? super T, ? extends R> mapper);

    <R> EnhancedStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

    EnhancedStream<T> distinct();

    EnhancedStream<T> sorted();

    EnhancedStream<T> sorted(Comparator<? super T> comparator);

    EnhancedStream<T> peek(Consumer<? super T> action);

    EnhancedStream<T> limit(long maxSize);

    EnhancedStream<T> skip(long n);

    EnhancedStream<T> takeWhile(Predicate<? super T> predicate);

    EnhancedStream<T> dropWhile(Predicate<? super T> predicate);

    EnhancedStream<T> sequential();

    EnhancedStream<T> parallel();

    EnhancedStream<T> unordered();

    EnhancedStream<T> onClose(Runnable closeHandler);

    // static factory methods
    @SafeVarargs
    @SuppressWarnings("varargs")
    static <E> EnhancedStream<E> of(E... elements) {
        return from(Stream.of(elements));
    }

    static <E> EnhancedStream<E> from(Stream<E> stream) {
        return (EnhancedStream<E>) Proxy.newProxyInstance(
                EnhancedStream.class.getClassLoader(),
                new Class<?>[]{EnhancedStream.class},
                new EnhancedStreamHandler<>(stream));
    }
}