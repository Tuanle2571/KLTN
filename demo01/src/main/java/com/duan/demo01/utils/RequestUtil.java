package com.duan.demo01.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static String getUrl(HttpServletRequest request)
    {
        return request.getRequestURI().toString();
    }
}
