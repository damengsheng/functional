import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * JavaCode
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 03/11/2019 12:26.
 */
public class JavaCode {

    private static final Logger log = Logger.getLogger(JavaCode.class.getCanonicalName());

    private       byte      b;
    private       Byte      bo;
    private final byte      B      = 0;
    private final Byte      BO     = 1;
    private       short     s;
    private       Short     so;
    private final short     S      = 0;
    private final Short     SO     = 1;
    private       int       i;
    private       Integer   io;
    private final int       I      = 0;
    private final Integer   IO     = 1;
    private       long      l;
    private       Long      lo;
    private final long      L      = 0L;
    private final Long      LO     = 1L;
    private       float     f;
    private       Float     fo;
    private final float     F      = 0.0F;
    private final Float     FO     = 1.0F;
    private       double    d;
    private       Double    dO;
    private final double    D      = 0.0D;
    private final Double    DO     = 1.0D;
    private       boolean   bl;
    private       Boolean   blo;
    private final boolean   BL     = false;
    private final Boolean   BLO    = true;
    private       char      c;
    private       Character co;
    private final char      C      = 'a';
    private final Character CO     = 'a';
    private       String    str;
    private final String    STR    = "string";
    private       byte[]    bytes;
    private       Byte[]    byteOa;
    private final byte[]    BYTES  = {0, 1, 2, 3, 4, 5};
    private final Byte[]    BYTEOA = {5, 4, 3, 2, 1, 0};

    Function<String, String> sayGreet = name -> "Hello ".concat(Optional.ofNullable(name).orElse("")).concat(" !");
    Consumer<String>         printOut = System.out::println;

    void sayGreet() {
        sayGreet.apply("yakir");
        printOut.accept("yakir");
    }

    public static void main(String[] args) {

        JavaCode jc = new JavaCode();
        jc.sayGreet();

        log.info("start main");

    }
}
