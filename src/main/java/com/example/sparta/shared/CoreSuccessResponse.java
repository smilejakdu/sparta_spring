package com.example.sparta.shared;

import lombok.Getter;


@Getter
public class CoreSuccessResponse {
    private Object data;

    public CoreSuccessResponse(Object data) {
        this.data = data;
    }
}

