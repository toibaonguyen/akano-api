package com.akano.api.application.common;

public interface GenericUseCase<TRequest extends BaseRequestModel<ID>, TResponse extends BaseResponseModel<TResult>, TResult, ID> {
    TResponse execute(TRequest request);
}
