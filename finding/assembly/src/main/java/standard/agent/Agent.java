package yakir.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * App
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/06 20:06.
 */
public class Agent {

    private static final Logger log = LogManager.getLogger(Agent.class);

    public static void main(final String[] args) {

        log.info("Run Args: {}", Arrays.toString(args));
        long time = Optional.ofNullable(args[0]).map(Long::parseLong).orElse(10L);
        String agent = Optional.ofNullable(args[1]).map(_arg -> {
            log.info("[Agent] Has Agent Arg: [{}]", _arg);
            return _arg;
        }).orElse(null);
        String agentPath = Optional.ofNullable(args[2]).map(_arg -> {
            log.info("[Agent] Has Agent Path: [{}]", _arg);
            return _arg;
        }).orElse(null);

        if (null != agent && null != agentPath && !"".equalsIgnoreCase(agentPath)) {
            dynamicAgent(agentPath);
        }
    }

    public static void dynamicAgent(String agentFilePath) {

        Optional<String> vmDescriptorId = Optional.ofNullable(VirtualMachine.list()
                .stream()
                .peek(_vm -> log.info("jvm: {}", _vm.displayName()))
                .filter(_vm -> _vm.displayName().contains(Agent.class.getSimpleName()))
                .findFirst().get().id());

        if (!vmDescriptorId.isPresent()) {
            log.error("Target Application Not Found .");
            return;
        }

        File agentFile = new File(agentFilePath);
        try {
            String jvmPid = vmDescriptorId.get();
            log.info("[Attching] to target JVM with PID: {}", jvmPid);
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent(agentFile.getAbsolutePath());
            jvm.detach();
        } catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException e) {
            log.error("Error: ", e);
        }

    }
}
