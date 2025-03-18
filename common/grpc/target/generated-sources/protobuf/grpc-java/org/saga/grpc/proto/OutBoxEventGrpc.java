package org.saga.grpc.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class OutBoxEventGrpc {

  private OutBoxEventGrpc() {}

  public static final java.lang.String SERVICE_NAME = "OutBoxEvent";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.saga.grpc.proto.OutBoxEventRequest,
      org.saga.grpc.proto.OutBoxEventResponse> getSaveOutBoxEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SaveOutBoxEvent",
      requestType = org.saga.grpc.proto.OutBoxEventRequest.class,
      responseType = org.saga.grpc.proto.OutBoxEventResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.saga.grpc.proto.OutBoxEventRequest,
      org.saga.grpc.proto.OutBoxEventResponse> getSaveOutBoxEventMethod() {
    io.grpc.MethodDescriptor<org.saga.grpc.proto.OutBoxEventRequest, org.saga.grpc.proto.OutBoxEventResponse> getSaveOutBoxEventMethod;
    if ((getSaveOutBoxEventMethod = OutBoxEventGrpc.getSaveOutBoxEventMethod) == null) {
      synchronized (OutBoxEventGrpc.class) {
        if ((getSaveOutBoxEventMethod = OutBoxEventGrpc.getSaveOutBoxEventMethod) == null) {
          OutBoxEventGrpc.getSaveOutBoxEventMethod = getSaveOutBoxEventMethod =
              io.grpc.MethodDescriptor.<org.saga.grpc.proto.OutBoxEventRequest, org.saga.grpc.proto.OutBoxEventResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SaveOutBoxEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.saga.grpc.proto.OutBoxEventRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.saga.grpc.proto.OutBoxEventResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OutBoxEventMethodDescriptorSupplier("SaveOutBoxEvent"))
              .build();
        }
      }
    }
    return getSaveOutBoxEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OutBoxEventStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OutBoxEventStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OutBoxEventStub>() {
        @java.lang.Override
        public OutBoxEventStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OutBoxEventStub(channel, callOptions);
        }
      };
    return OutBoxEventStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static OutBoxEventBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OutBoxEventBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OutBoxEventBlockingV2Stub>() {
        @java.lang.Override
        public OutBoxEventBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OutBoxEventBlockingV2Stub(channel, callOptions);
        }
      };
    return OutBoxEventBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OutBoxEventBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OutBoxEventBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OutBoxEventBlockingStub>() {
        @java.lang.Override
        public OutBoxEventBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OutBoxEventBlockingStub(channel, callOptions);
        }
      };
    return OutBoxEventBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OutBoxEventFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OutBoxEventFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OutBoxEventFutureStub>() {
        @java.lang.Override
        public OutBoxEventFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OutBoxEventFutureStub(channel, callOptions);
        }
      };
    return OutBoxEventFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void saveOutBoxEvent(org.saga.grpc.proto.OutBoxEventRequest request,
        io.grpc.stub.StreamObserver<org.saga.grpc.proto.OutBoxEventResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSaveOutBoxEventMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service OutBoxEvent.
   */
  public static abstract class OutBoxEventImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return OutBoxEventGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service OutBoxEvent.
   */
  public static final class OutBoxEventStub
      extends io.grpc.stub.AbstractAsyncStub<OutBoxEventStub> {
    private OutBoxEventStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OutBoxEventStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OutBoxEventStub(channel, callOptions);
    }

    /**
     */
    public void saveOutBoxEvent(org.saga.grpc.proto.OutBoxEventRequest request,
        io.grpc.stub.StreamObserver<org.saga.grpc.proto.OutBoxEventResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSaveOutBoxEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service OutBoxEvent.
   */
  public static final class OutBoxEventBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<OutBoxEventBlockingV2Stub> {
    private OutBoxEventBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OutBoxEventBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OutBoxEventBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public org.saga.grpc.proto.OutBoxEventResponse saveOutBoxEvent(org.saga.grpc.proto.OutBoxEventRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSaveOutBoxEventMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service OutBoxEvent.
   */
  public static final class OutBoxEventBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<OutBoxEventBlockingStub> {
    private OutBoxEventBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OutBoxEventBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OutBoxEventBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.saga.grpc.proto.OutBoxEventResponse saveOutBoxEvent(org.saga.grpc.proto.OutBoxEventRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSaveOutBoxEventMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service OutBoxEvent.
   */
  public static final class OutBoxEventFutureStub
      extends io.grpc.stub.AbstractFutureStub<OutBoxEventFutureStub> {
    private OutBoxEventFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OutBoxEventFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OutBoxEventFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.saga.grpc.proto.OutBoxEventResponse> saveOutBoxEvent(
        org.saga.grpc.proto.OutBoxEventRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSaveOutBoxEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_OUT_BOX_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_OUT_BOX_EVENT:
          serviceImpl.saveOutBoxEvent((org.saga.grpc.proto.OutBoxEventRequest) request,
              (io.grpc.stub.StreamObserver<org.saga.grpc.proto.OutBoxEventResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSaveOutBoxEventMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.saga.grpc.proto.OutBoxEventRequest,
              org.saga.grpc.proto.OutBoxEventResponse>(
                service, METHODID_SAVE_OUT_BOX_EVENT)))
        .build();
  }

  private static abstract class OutBoxEventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OutBoxEventBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.saga.grpc.proto.OutboxEventProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OutBoxEvent");
    }
  }

  private static final class OutBoxEventFileDescriptorSupplier
      extends OutBoxEventBaseDescriptorSupplier {
    OutBoxEventFileDescriptorSupplier() {}
  }

  private static final class OutBoxEventMethodDescriptorSupplier
      extends OutBoxEventBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    OutBoxEventMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (OutBoxEventGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OutBoxEventFileDescriptorSupplier())
              .addMethod(getSaveOutBoxEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
