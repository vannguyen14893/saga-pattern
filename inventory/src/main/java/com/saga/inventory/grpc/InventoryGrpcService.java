package com.saga.inventory.grpc;

import com.google.gson.Gson;
import com.saga.inventory.entity.Inventory;
import com.saga.inventory.service.InventoryService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.saga.grpc.proto.CheckInventoryRequest;
import org.saga.grpc.proto.CheckInventoryResponse;
import org.saga.grpc.proto.InventoryGrpc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class InventoryGrpcService extends InventoryGrpc.InventoryImplBase {
    private final InventoryService inventoryService;

    @Override
    public void checkInventory(CheckInventoryRequest checkInventoryRequest, StreamObserver<CheckInventoryResponse> responseObserver) {
        String productId = checkInventoryRequest.getProductId();
        List<Long> productIds =Arrays.stream(productId.split(",")).map(Long::valueOf).collect(Collectors.toList());
        List<Inventory> inventory = inventoryService.findAllByProductIdIn(productIds);
        String data = new Gson().toJson(inventory);
        CheckInventoryResponse checkInventoryResponse = CheckInventoryResponse.newBuilder().setData(data).build();
        responseObserver.onNext(checkInventoryResponse);
        responseObserver.onCompleted();
    }
}
