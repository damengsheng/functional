package yakir.modular.asm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Assembly
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/02 13:13.
 */
public class Assembly {

    public static void main(String[] args) throws IOException, InterruptedException {

        String flags = "-XX:+AlwaysPreTouch -XX:CICompilerCount=6 -XX:CompileThreshold=100 -XX:CompressedClassSpaceSize=528482304 -XX:ConcGCThreads=4 -XX:G1NewSizePercent=6 -XX:G1ReservePercent=15 -XX:+HeapDumpOnOutOfMemoryError -XX:InitialHeapSize=2147483648 -XX:InitiatingHeapOccupancyPercent=30 -XX:MaxGCPauseMillis=1200 -XX:MaxHeapSize=2147483648 -XX:MaxMetaspaceSize=536870912 -XX:MinMetaspaceFreeRatio=60 -XX:-OmitStackTraceInFastThrow -XX:OnStackReplacePercentage=34 -XX:ParallelGCThreads=8 -XX:+PrintCommandLineFlags -XX:+PrintFlagsFinal -XX:ReservedCodeCacheSize=268435456 -XX:+SegmentedCodeCache -XX:SoftRefLRUPolicyMSPerMB=50 -XX:SurvivorRatio=8 -XX:ThreadStackSize=1024 -XX:+TieredCompilation -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions -XX:UseAVX=2 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseCounterDecay -XX:+UseNUMA -XX:+UseSerialGC";
        flagsFinal(Arrays.asList(flags.split(" ")));

    }

    private static List<String> flagsFinal(final List<String> userDefinedJvmOptions) throws InterruptedException, IOException {
        final String java = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        final List<String> command =
                Stream.of(Stream.of(java), userDefinedJvmOptions.stream(), Stream.of("-XX:+PrintFlagsFinal"), Stream.of("-version"))
                        .reduce(Stream::concat)
                        .get()
                        .collect(Collectors.toUnmodifiableList());
        final Process      process = new ProcessBuilder().command(command).start();
        final List<String> output  = readLinesFromInputStream(process.getInputStream());
        final List<String> error   = readLinesFromInputStream(process.getErrorStream());
        final int          status  = process.waitFor();
        if (status != 0) {
            final String message = String.format(
                    Locale.ROOT,
                    "starting java failed with [%d]\noutput:\n%s\nerror:\n%s",
                    status,
                    String.join("\n", output),
                    String.join("\n", error));
            throw new RuntimeException(message);
        } else {
            return output;
        }
    }

    private static List<String> readLinesFromInputStream(final InputStream is) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            return br.lines().collect(Collectors.toUnmodifiableList());
        }
    }
}
