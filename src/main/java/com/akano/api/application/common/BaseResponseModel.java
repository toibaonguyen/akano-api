package com.akano.api.application.common;

public interface BaseResponseModel<T> {
    String getCode();
    T getData();
}
