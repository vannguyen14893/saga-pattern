package com.saga.orchestration.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends org.springframework.grpc.sample.proto.SimpleGrpc.SimpleImplBase {
    @Override
    public void sayHello(org.springframework.grpc.sample.proto.HelloRequest req, StreamObserver<org.springframework.grpc.sample.proto.HelloReply> responseObserver) {
        if (req.getName().startsWith("error")) {
            throw new IllegalArgumentException("Bad name: " + req.getName());
        }
        if (req.getName().startsWith("internal")) {
            throw new RuntimeException();
        }
        org.springframework.grpc.sample.proto.HelloReply reply = org.springframework.grpc.sample.proto.HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
