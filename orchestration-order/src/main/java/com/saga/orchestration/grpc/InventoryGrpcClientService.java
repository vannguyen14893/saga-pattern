package com.saga.orchestration.grpc;

import com.google.gson.Gson;
import com.saga.dto.request.CreateOrderRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.saga.grpc.proto.CheckInventoryRequest;
import org.saga.grpc.proto.CheckInventoryResponse;
import org.saga.grpc.proto.InventoryGrpc;
import org.springframework.stereotype.Service;

/**
 * gRPC client service for inventory-related operations.
 * Handles communication with the inventory service using gRPC protocol.
 * Part of the saga pattern implementation for coordinating inventory checks.
 */
@Service
public class InventoryGrpcClientService {
    @GrpcClient("inventory")
    private InventoryGrpc.InventoryBlockingStub stub;

    /**
     * Checks inventory availability for the given order request.
     * Converts the order request to JSON and sends it via gRPC to the inventory service.
     *
     * @param createOrderRequest The order request containing items to check
     * @return Response data from the inventory service as a String
     */
    public String checkInventory(CreateOrderRequest createOrderRequest) {
        String payload = new Gson().toJson(createOrderRequest.orderRequests());
        CheckInventoryRequest checkInventoryRequest = CheckInventoryRequest.newBuilder().setPayload(payload).build();
        CheckInventoryResponse checkInventoryResponse = stub.checkInventory(checkInventoryRequest);
        return checkInventoryResponse.getData();
    }
}
