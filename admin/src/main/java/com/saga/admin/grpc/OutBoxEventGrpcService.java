package com.saga.admin.grpc;

import com.saga.admin.entity.OutboxEvent;
import com.saga.admin.service.outboxevent.OutBoxEventService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.saga.grpc.proto.OutBoxEventGrpc;
import org.saga.grpc.proto.OutBoxEventRequest;
import org.saga.grpc.proto.OutBoxEventResponse;

/**
 * gRPC service implementation for handling OutBox events.
 * This service extends the generated OutBoxEventGrpc base class and provides
 * functionality to save outbox events through gRPC communication.
 * The service integrates with OutBoxEventService for actual event persistence.
 */
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
