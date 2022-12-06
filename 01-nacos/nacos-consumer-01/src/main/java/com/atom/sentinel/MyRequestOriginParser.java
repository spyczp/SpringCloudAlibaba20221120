package com.atom.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyRequestOriginParser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {

        String origin = (String) request.getAttribute("origin");
        // 授权参数保存到请求头里好一点
        // String token = request.getHeader("token");

        return origin;
    }
}
