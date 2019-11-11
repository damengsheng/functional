package framwk.benchmark.jmh;

public interface Calculator {

    /**
     * calculate sum of an integer array
     *
     * @param numbers {@linkplain int[]}
     * @return long
     */
    long sum(int[] numbers);

    /**
     * shutdown pool or reclaim any related resources
     */
    void shutdown();
}