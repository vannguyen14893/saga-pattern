package com.saga.inventory.grpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.saga.inventory.service.InventoryService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.saga.grpc.proto.CheckInventoryRequest;
import org.saga.grpc.proto.CheckInventoryResponse;
import org.saga.grpc.proto.InventoryGrpc;

import java.util.List;

import static com.jayway.jsonpath.JsonPath.using;

@GrpcService
@RequiredArgsConstructor
public class InventoryGrpcService extends InventoryGrpc.InventoryImplBase {
    private final InventoryService inventoryService;

    @Override
    public void checkInventory(CheckInventoryRequest checkInventoryRequest, StreamObserver<CheckInventoryResponse> responseObserver) {
        String payload = checkInventoryRequest.getPayload();
        Configuration configuration = Configuration.builder()
                .mappingProvider(new JacksonMappingProvider(new ObjectMapper()))
                .options(Option.DEFAULT_PATH_LEAF_TO_NULL)
                .build();
        TypeRef<List<Long>> typeProductIds = new TypeRef<>() {};
        List<Long> productIds = using(configuration).parse(payload).read("$[*].productId", typeProductIds);
        // Check which product IDs do not have enough stock
        String data = new Gson().toJson(inventoryService.findAllByProductIdIn(productIds));
        CheckInventoryResponse checkInventoryResponse = CheckInventoryResponse.newBuilder().build();
        responseObserver.onNext(checkInventoryResponse);
        responseObserver.onCompleted();
    }
}
