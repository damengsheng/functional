package framewk.dubbo.service;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;

/**
 * DubboProviderConfig
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2019/02/15 12:52.
 */
public class HelloGenericService implements GenericService {

    private static final Logger log = LogManager.getLogger(HelloGenericService.class);

    @Override
    public String $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        String rt = method + " " + Arrays.toString(parameterTypes) + " " + Arrays.toString(args);
        log.info("Input [{}]", rt);

        return rt;
    }
}
