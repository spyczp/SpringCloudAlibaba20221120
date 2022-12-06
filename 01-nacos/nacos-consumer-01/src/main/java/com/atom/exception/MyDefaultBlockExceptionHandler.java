package com.atom.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.atom.common.RespObj;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyDefaultBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info("MyDefaultBlockExceptionHandler.....");

        RespObj respObj = null;

        if(e instanceof FlowException){
            respObj = RespObj.builder().statusCode(100).statusMsg("接口限流了").build();
        }else if(e instanceof ParamFlowException){
            respObj = RespObj.builder().statusCode(101).statusMsg("热点参数限流了").build();
        }else if(e instanceof DegradeException){
            respObj = RespObj.builder().statusCode(102).statusMsg("服务降级了").build();
        }else if(e instanceof AuthorityException){
            respObj = RespObj.builder().statusCode(103).statusMsg("授权规则不通过").build();
        }else if(e instanceof SystemBlockException){
            respObj = RespObj.builder().statusCode(104).statusMsg("触发系统保护规则").build();
        }

        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getWriter(), respObj);
    }
}
