package com.fan.wang.base.controller;

import com.fan.wang.common.api.BaseApiService;

import javax.servlet.http.HttpServletRequest;

public class BaseController extends BaseApiService {
    public static final String ERROR = "common/error";

    public String setError(HttpServletRequest request, String msg, String addres) {
        request.setAttribute("error", msg);
        return addres;
    }
}
