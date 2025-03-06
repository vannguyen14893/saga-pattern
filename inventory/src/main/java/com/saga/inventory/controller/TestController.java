package com.saga.inventory.controller;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.grpc.sample.proto.HelloReply;
import org.springframework.grpc.sample.proto.HelloRequest;
import org.springframework.grpc.sample.proto.SimpleGrpc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @GrpcClient("hello")
    private SimpleGrpc.SimpleBlockingStub stub;

    @GetMapping("test")

    public void test() {
        HelloRequest helloRequest = HelloRequest.newBuilder().setName("ndvan").build();
        HelloReply helloReply = stub.sayHello(helloRequest);
        log.info("response ------ {}", helloReply);
    }
}
