public class Lambda {

    long a;

    public static void main(String[] args) {

        Lambda lambda = new Lambda();

        Runnable[] ra = new Runnable[]{
                () -> lambda.a++,
                () -> lambda.a--,
                () -> lambda.a++,
                };
        for (long j = 0; j < 10_0000_0000; j++)
             ra[(int) (j % 3)].run();
        System.out.println(lambda.a);
    }
}
