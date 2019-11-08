package standard.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import standard.tools.NetUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TestNetUtils
 *
 * @author yakir <a href="yakirchen.com">yakirchen.com</a> on 2019/10/26 10:57.
 */
public class TestNetUtils {

    private static final Logger log = LogManager.getLogger(TestNetUtils.class);

    public static void main(String[] args) {
        List<String> ips    = NetUtils.hostAddress();
        String       ipsStr = Optional.of(ips).stream().flatMap(Collection::stream).collect(Collectors.joining(",\n"));
        log.info("Host Address {}", ipsStr);
    }
}
