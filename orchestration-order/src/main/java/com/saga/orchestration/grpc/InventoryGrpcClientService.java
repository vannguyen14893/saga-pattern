package com.saga.orchestration.grpc;

import com.google.gson.Gson;
import com.saga.dto.request.CreateOrderRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.saga.grpc.proto.CheckInventoryRequest;
import org.saga.grpc.proto.CheckInventoryResponse;
import org.saga.grpc.proto.InventoryGrpc;
import org.springframework.stereotype.Service;

@Service
public class InventoryGrpcClientService {
    @GrpcClient("inventory")
    private InventoryGrpc.InventoryBlockingStub stub;

    public String checkInventory(CreateOrderRequest createOrderRequest) {
        String payload = new Gson().toJson(createOrderRequest.orderRequests());
        CheckInventoryRequest checkInventoryRequest = CheckInventoryRequest.newBuilder().setPayload(payload).build();
        CheckInventoryResponse checkInventoryResponse = stub.checkInventory(checkInventoryRequest);
        return checkInventoryResponse.getData();
    }
}
