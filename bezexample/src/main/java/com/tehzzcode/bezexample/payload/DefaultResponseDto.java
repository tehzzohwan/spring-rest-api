package com.tehzzcode.bezexample.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefaultResponseDto<T> {
    private String statusCode;
    private String message;
    private String errorMessage;
    private T data;
}
