package com.saga.admin.grpc;

import com.saga.admin.entity.OutboxEvent;
import com.saga.admin.service.OutBoxEventService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.saga.grpc.proto.OutBoxEventGrpc;
import org.saga.grpc.proto.OutBoxEventRequest;
import org.saga.grpc.proto.OutBoxEventResponse;

@GrpcService
@RequiredArgsConstructor
public class OutBoxEventGrpcService extends OutBoxEventGrpc.OutBoxEventImplBase {
    private final OutBoxEventService outBoxEventService;

    @Override
    public void saveOutBoxEvent(OutBoxEventRequest request, StreamObserver<OutBoxEventResponse> responseObserver) {
        outBoxEventService.create(new OutboxEvent(request.getId(),request.getAggregateType(),
                request.getAggregateId(),request.getEventType(),request.getPayload()));
        OutBoxEventResponse outBoxEventResponse = OutBoxEventResponse.newBuilder().setData(request.getId()).build();
        responseObserver.onNext(outBoxEventResponse);
        responseObserver.onCompleted();
    }

}
