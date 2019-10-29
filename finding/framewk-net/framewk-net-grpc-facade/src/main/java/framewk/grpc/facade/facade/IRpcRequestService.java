package framewk.grpc.facade.facade;

import framewk.grpc.facade.dto.RpcRequest;
import framewk.grpc.facade.dto.RpcResponse;

/**
 * IRpcRequestService
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/03/26 23:24.
 */
public interface IRpcRequestService {

    RpcResponse request(RpcRequest rpcReq);
}
