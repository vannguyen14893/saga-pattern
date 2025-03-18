// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: outbox_event.proto

// Protobuf Java Version: 3.25.6
package org.saga.grpc.proto;

/**
 * Protobuf type {@code OutBoxEventRequest}
 */
public final class OutBoxEventRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:OutBoxEventRequest)
    OutBoxEventRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use OutBoxEventRequest.newBuilder() to construct.
  private OutBoxEventRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private OutBoxEventRequest() {
    id_ = "";
    aggregateType_ = "";
    aggregateId_ = "";
    eventType_ = "";
    payload_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new OutBoxEventRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.saga.grpc.proto.OutboxEventProto.internal_static_OutBoxEventRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.saga.grpc.proto.OutboxEventProto.internal_static_OutBoxEventRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.saga.grpc.proto.OutBoxEventRequest.class, org.saga.grpc.proto.OutBoxEventRequest.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object id_ = "";
  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AGGREGATETYPE_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object aggregateType_ = "";
  /**
   * <code>string aggregateType = 2;</code>
   * @return The aggregateType.
   */
  @java.lang.Override
  public java.lang.String getAggregateType() {
    java.lang.Object ref = aggregateType_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      aggregateType_ = s;
      return s;
    }
  }
  /**
   * <code>string aggregateType = 2;</code>
   * @return The bytes for aggregateType.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAggregateTypeBytes() {
    java.lang.Object ref = aggregateType_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      aggregateType_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int AGGREGATEID_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object aggregateId_ = "";
  /**
   * <code>string aggregateId = 3;</code>
   * @return The aggregateId.
   */
  @java.lang.Override
  public java.lang.String getAggregateId() {
    java.lang.Object ref = aggregateId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      aggregateId_ = s;
      return s;
    }
  }
  /**
   * <code>string aggregateId = 3;</code>
   * @return The bytes for aggregateId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getAggregateIdBytes() {
    java.lang.Object ref = aggregateId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      aggregateId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EVENTTYPE_FIELD_NUMBER = 4;
  @SuppressWarnings("serial")
  private volatile java.lang.Object eventType_ = "";
  /**
   * <code>string eventType = 4;</code>
   * @return The eventType.
   */
  @java.lang.Override
  public java.lang.String getEventType() {
    java.lang.Object ref = eventType_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      eventType_ = s;
      return s;
    }
  }
  /**
   * <code>string eventType = 4;</code>
   * @return The bytes for eventType.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getEventTypeBytes() {
    java.lang.Object ref = eventType_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      eventType_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAYLOAD_FIELD_NUMBER = 5;
  @SuppressWarnings("serial")
  private volatile java.lang.Object payload_ = "";
  /**
   * <code>string payload = 5;</code>
   * @return The payload.
   */
  @java.lang.Override
  public java.lang.String getPayload() {
    java.lang.Object ref = payload_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      payload_ = s;
      return s;
    }
  }
  /**
   * <code>string payload = 5;</code>
   * @return The bytes for payload.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPayloadBytes() {
    java.lang.Object ref = payload_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      payload_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(aggregateType_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, aggregateType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(aggregateId_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, aggregateId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(eventType_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, eventType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(payload_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, payload_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(aggregateType_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, aggregateType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(aggregateId_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, aggregateId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(eventType_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, eventType_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(payload_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, payload_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.saga.grpc.proto.OutBoxEventRequest)) {
      return super.equals(obj);
    }
    org.saga.grpc.proto.OutBoxEventRequest other = (org.saga.grpc.proto.OutBoxEventRequest) obj;

    if (!getId()
        .equals(other.getId())) return false;
    if (!getAggregateType()
        .equals(other.getAggregateType())) return false;
    if (!getAggregateId()
        .equals(other.getAggregateId())) return false;
    if (!getEventType()
        .equals(other.getEventType())) return false;
    if (!getPayload()
        .equals(other.getPayload())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId().hashCode();
    hash = (37 * hash) + AGGREGATETYPE_FIELD_NUMBER;
    hash = (53 * hash) + getAggregateType().hashCode();
    hash = (37 * hash) + AGGREGATEID_FIELD_NUMBER;
    hash = (53 * hash) + getAggregateId().hashCode();
    hash = (37 * hash) + EVENTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getEventType().hashCode();
    hash = (37 * hash) + PAYLOAD_FIELD_NUMBER;
    hash = (53 * hash) + getPayload().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static org.saga.grpc.proto.OutBoxEventRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static org.saga.grpc.proto.OutBoxEventRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.saga.grpc.proto.OutBoxEventRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.saga.grpc.proto.OutBoxEventRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code OutBoxEventRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:OutBoxEventRequest)
      org.saga.grpc.proto.OutBoxEventRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.saga.grpc.proto.OutboxEventProto.internal_static_OutBoxEventRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.saga.grpc.proto.OutboxEventProto.internal_static_OutBoxEventRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.saga.grpc.proto.OutBoxEventRequest.class, org.saga.grpc.proto.OutBoxEventRequest.Builder.class);
    }

    // Construct using org.saga.grpc.proto.OutBoxEventRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      id_ = "";
      aggregateType_ = "";
      aggregateId_ = "";
      eventType_ = "";
      payload_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.saga.grpc.proto.OutboxEventProto.internal_static_OutBoxEventRequest_descriptor;
    }

    @java.lang.Override
    public org.saga.grpc.proto.OutBoxEventRequest getDefaultInstanceForType() {
      return org.saga.grpc.proto.OutBoxEventRequest.getDefaultInstance();
    }

    @java.lang.Override
    public org.saga.grpc.proto.OutBoxEventRequest build() {
      org.saga.grpc.proto.OutBoxEventRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.saga.grpc.proto.OutBoxEventRequest buildPartial() {
      org.saga.grpc.proto.OutBoxEventRequest result = new org.saga.grpc.proto.OutBoxEventRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(org.saga.grpc.proto.OutBoxEventRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.id_ = id_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.aggregateType_ = aggregateType_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.aggregateId_ = aggregateId_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.eventType_ = eventType_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.payload_ = payload_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.saga.grpc.proto.OutBoxEventRequest) {
        return mergeFrom((org.saga.grpc.proto.OutBoxEventRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.saga.grpc.proto.OutBoxEventRequest other) {
      if (other == org.saga.grpc.proto.OutBoxEventRequest.getDefaultInstance()) return this;
      if (!other.getId().isEmpty()) {
        id_ = other.id_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getAggregateType().isEmpty()) {
        aggregateType_ = other.aggregateType_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      if (!other.getAggregateId().isEmpty()) {
        aggregateId_ = other.aggregateId_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      if (!other.getEventType().isEmpty()) {
        eventType_ = other.eventType_;
        bitField0_ |= 0x00000008;
        onChanged();
      }
      if (!other.getPayload().isEmpty()) {
        payload_ = other.payload_;
        bitField0_ |= 0x00000010;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              id_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              aggregateType_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              aggregateId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            case 34: {
              eventType_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000008;
              break;
            } // case 34
            case 42: {
              payload_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000010;
              break;
            } // case 42
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object id_ = "";
    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      id_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      id_ = getDefaultInstance().getId();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string id = 1;</code>
     * @param value The bytes for id to set.
     * @return This builder for chaining.
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      id_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object aggregateType_ = "";
    /**
     * <code>string aggregateType = 2;</code>
     * @return The aggregateType.
     */
    public java.lang.String getAggregateType() {
      java.lang.Object ref = aggregateType_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        aggregateType_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string aggregateType = 2;</code>
     * @return The bytes for aggregateType.
     */
    public com.google.protobuf.ByteString
        getAggregateTypeBytes() {
      java.lang.Object ref = aggregateType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        aggregateType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string aggregateType = 2;</code>
     * @param value The aggregateType to set.
     * @return This builder for chaining.
     */
    public Builder setAggregateType(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      aggregateType_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string aggregateType = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAggregateType() {
      aggregateType_ = getDefaultInstance().getAggregateType();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string aggregateType = 2;</code>
     * @param value The bytes for aggregateType to set.
     * @return This builder for chaining.
     */
    public Builder setAggregateTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      aggregateType_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }

    private java.lang.Object aggregateId_ = "";
    /**
     * <code>string aggregateId = 3;</code>
     * @return The aggregateId.
     */
    public java.lang.String getAggregateId() {
      java.lang.Object ref = aggregateId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        aggregateId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string aggregateId = 3;</code>
     * @return The bytes for aggregateId.
     */
    public com.google.protobuf.ByteString
        getAggregateIdBytes() {
      java.lang.Object ref = aggregateId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        aggregateId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string aggregateId = 3;</code>
     * @param value The aggregateId to set.
     * @return This builder for chaining.
     */
    public Builder setAggregateId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      aggregateId_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>string aggregateId = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearAggregateId() {
      aggregateId_ = getDefaultInstance().getAggregateId();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>string aggregateId = 3;</code>
     * @param value The bytes for aggregateId to set.
     * @return This builder for chaining.
     */
    public Builder setAggregateIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      aggregateId_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    private java.lang.Object eventType_ = "";
    /**
     * <code>string eventType = 4;</code>
     * @return The eventType.
     */
    public java.lang.String getEventType() {
      java.lang.Object ref = eventType_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        eventType_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string eventType = 4;</code>
     * @return The bytes for eventType.
     */
    public com.google.protobuf.ByteString
        getEventTypeBytes() {
      java.lang.Object ref = eventType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        eventType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string eventType = 4;</code>
     * @param value The eventType to set.
     * @return This builder for chaining.
     */
    public Builder setEventType(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      eventType_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>string eventType = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearEventType() {
      eventType_ = getDefaultInstance().getEventType();
      bitField0_ = (bitField0_ & ~0x00000008);
      onChanged();
      return this;
    }
    /**
     * <code>string eventType = 4;</code>
     * @param value The bytes for eventType to set.
     * @return This builder for chaining.
     */
    public Builder setEventTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      eventType_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }

    private java.lang.Object payload_ = "";
    /**
     * <code>string payload = 5;</code>
     * @return The payload.
     */
    public java.lang.String getPayload() {
      java.lang.Object ref = payload_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        payload_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string payload = 5;</code>
     * @return The bytes for payload.
     */
    public com.google.protobuf.ByteString
        getPayloadBytes() {
      java.lang.Object ref = payload_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        payload_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string payload = 5;</code>
     * @param value The payload to set.
     * @return This builder for chaining.
     */
    public Builder setPayload(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      payload_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>string payload = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearPayload() {
      payload_ = getDefaultInstance().getPayload();
      bitField0_ = (bitField0_ & ~0x00000010);
      onChanged();
      return this;
    }
    /**
     * <code>string payload = 5;</code>
     * @param value The bytes for payload to set.
     * @return This builder for chaining.
     */
    public Builder setPayloadBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      payload_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:OutBoxEventRequest)
  }

  // @@protoc_insertion_point(class_scope:OutBoxEventRequest)
  private static final org.saga.grpc.proto.OutBoxEventRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.saga.grpc.proto.OutBoxEventRequest();
  }

  public static org.saga.grpc.proto.OutBoxEventRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<OutBoxEventRequest>
      PARSER = new com.google.protobuf.AbstractParser<OutBoxEventRequest>() {
    @java.lang.Override
    public OutBoxEventRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<OutBoxEventRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<OutBoxEventRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.saga.grpc.proto.OutBoxEventRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

