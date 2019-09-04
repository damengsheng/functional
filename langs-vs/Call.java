public class Call {

    interface I {

        void f();

        long get();
    }

    static class S1 implements I {

        long a;

        @Override
        public void f() {
            a++;
        }

        @Override
        public long get() {
            return a;
        }
    }

    static class S2 implements I {

        long a;

        @Override
        public void f() {
            a--;
        }

        @Override
        public long get() {
            return a;
        }
    }

    public static void main(String[] args) {

        I[] ia = new I[]{
                new S1(),
                new S2(),
                new S1()
        };

        for (long j = 0; j < 10_0000_0000; j++)
             ia[(int) (j % 3)].f();

        System.out.println(ia[0].get() + ia[1].get() + ia[2].get());
    }
}
