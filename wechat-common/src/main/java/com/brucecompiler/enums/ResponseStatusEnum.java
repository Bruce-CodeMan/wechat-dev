package com.brucecompiler.enums;

import com.brucecompiler.constants.IsCallSuccessConstant;
import com.brucecompiler.constants.ResponseCodeConstant;
import com.brucecompiler.constants.ResponseMessageConstant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatusEnum {

    SUCCESS(ResponseCodeConstant.SUCCESS, IsCallSuccessConstant.SUCCESS, ResponseMessageConstant.SUCCESS),
    FAILED(ResponseCodeConstant.FAILED, IsCallSuccessConstant.FAILED, ResponseMessageConstant.FAILED);

    // 响应业务状态
    private final Integer status;

    // 调用是否成功
    private final Boolean success;

    // 响应消息
    private final String message;
}
