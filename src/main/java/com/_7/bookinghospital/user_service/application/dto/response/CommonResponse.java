package com._7.bookinghospital.user_service.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
// feignClient 응답용, 클라이언트 응답용
public class CommonResponse<T> {
    private String message;
    private T data;

    @Builder
    private CommonResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // 성공 응답 (메시지를 동적으로 설정)
    public static <T> CommonResponse<T> success(T data, String message) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }

    // 실패 응답 (메시지를 동적으로 설정)
    public static <T> CommonResponse<T> fail(String message) {
        return CommonResponse.<T>builder()
                .message(message)
                .data(null)
                .build();
    }
}
