package com.touresbalon.api;

import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiUtil {
    public static void setResponse(NativeWebRequest req, String contentType, Object request) {
        try {
            HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Content-Type", contentType);
            res.getWriter().print(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
