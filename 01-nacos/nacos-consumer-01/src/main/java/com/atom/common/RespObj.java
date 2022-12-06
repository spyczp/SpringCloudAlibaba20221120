package com.atom.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespObj {

    private Integer statusCode;

    private String statusMsg;

}
