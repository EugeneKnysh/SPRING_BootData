package com.example.bootdata;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Response<T> {
    private String status;
    private T body;
}
