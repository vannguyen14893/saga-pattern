// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: outbox_event.proto

// Protobuf Java Version: 3.25.6
package org.saga.grpc.proto;

public interface OutBoxEventRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:OutBoxEventRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string aggregateType = 2;</code>
   * @return The aggregateType.
   */
  java.lang.String getAggregateType();
  /**
   * <code>string aggregateType = 2;</code>
   * @return The bytes for aggregateType.
   */
  com.google.protobuf.ByteString
      getAggregateTypeBytes();

  /**
   * <code>string aggregateId = 3;</code>
   * @return The aggregateId.
   */
  java.lang.String getAggregateId();
  /**
   * <code>string aggregateId = 3;</code>
   * @return The bytes for aggregateId.
   */
  com.google.protobuf.ByteString
      getAggregateIdBytes();

  /**
   * <code>string eventType = 4;</code>
   * @return The eventType.
   */
  java.lang.String getEventType();
  /**
   * <code>string eventType = 4;</code>
   * @return The bytes for eventType.
   */
  com.google.protobuf.ByteString
      getEventTypeBytes();

  /**
   * <code>string payload = 5;</code>
   * @return The payload.
   */
  java.lang.String getPayload();
  /**
   * <code>string payload = 5;</code>
   * @return The bytes for payload.
   */
  com.google.protobuf.ByteString
      getPayloadBytes();
}
