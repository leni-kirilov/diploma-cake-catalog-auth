package com.cakecatalog.srv.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RequestMapping
public class LoggingInterceptor extends HandlerInterceptorAdapter {

  private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

  private static Map<String, String> headerToMdcNameMap = new HashMap<>();

  static {
    headerToMdcNameMap.put("x-cake-catalog-request-id", "requestId");
    headerToMdcNameMap.put("x-cake-catalog-source-id", "sourceId");
  }

  @Override
  public boolean preHandle(
    HttpServletRequest req,
    HttpServletResponse response,
    Object handler) throws Exception {

    updateMdcFromServletRequest(req);

    return true;
  }

  public static void updateMdcFromServletRequest(HttpServletRequest req) {
    Enumeration headerNames = req.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = (String) headerNames.nextElement();
      if (headerToMdcNameMap.containsKey(headerName)) {
        MDC.put(headerToMdcNameMap.get(headerName), req.getHeader(headerName));
      }
    }
  }
}
