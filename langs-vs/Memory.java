public class Memory {

    static class E {
        int a;

        E(int a) {
            this.a = a;
        }
    }

    public static void main(String[] args) {

        final long ARRAY_COUNT = 10000L;
        final long TEST_COUNT  = ARRAY_COUNT * 10_0000L;

        E[] es = new E[(int) ARRAY_COUNT];
        for (long i = 0; i < TEST_COUNT; i++)
             es[(int) (i * 123456789L % ARRAY_COUNT)] = new E((int) i);

        long n = 0;
        for (long i = 0; i < ARRAY_COUNT; i++) {
            E e = es[(int) i];
            if (e != null)
                n += e.a;
        }
        System.out.println(n);
    }
}
