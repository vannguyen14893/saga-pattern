// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: inventory.proto

// Protobuf Java Version: 3.25.6
package org.saga.grpc.proto;

public final class InventoryProto {
  private InventoryProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CheckInventoryRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CheckInventoryRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CheckInventoryResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CheckInventoryResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017inventory.proto\"(\n\025CheckInventoryReque" +
      "st\022\017\n\007payload\030\001 \001(\t\"&\n\026CheckInventoryRes" +
      "ponse\022\014\n\004data\030\001 \001(\t2P\n\tInventory\022C\n\016Chec" +
      "kInventory\022\026.CheckInventoryRequest\032\027.Che" +
      "ckInventoryResponse\"\000B\'\n\023org.saga.grpc.p" +
      "rotoB\016InventoryProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_CheckInventoryRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CheckInventoryRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CheckInventoryRequest_descriptor,
        new java.lang.String[] { "Payload", });
    internal_static_CheckInventoryResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_CheckInventoryResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CheckInventoryResponse_descriptor,
        new java.lang.String[] { "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
