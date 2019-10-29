package framewk.grpc.server;

import framewk.grpc.greeting.GreeterGrpc;
import framewk.grpc.greeting.HelloRequest;
import framewk.grpc.greeting.HelloResponse;
import io.grpc.stub.StreamObserver;

/**
 * GreetingService
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 2018/10/19 15:38.
 */
public class GreetingService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        HelloResponse reply = HelloResponse.newBuilder()
                .setMessage("Hello "
                        .concat(request.getName())
                        .concat(" !"))
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
