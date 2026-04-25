package com.akano.api.application.catalog.model.response;

import com.akano.api.application.common.BaseResponseModel;
import com.akano.api.application.common.success.SuccessCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CreateBookResponseModel implements BaseResponseModel<CreateBookInfomationResult> {

    CreateBookInfomationResult data;
    String code;

    public CreateBookResponseModel(CreateBookInfomationResult data) {
        this.data = data;
        this.code = SuccessCode.BOOK_CREATED;
    }
}
