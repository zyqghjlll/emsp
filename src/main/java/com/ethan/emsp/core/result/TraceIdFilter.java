package com.ethan.emsp.core.result;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;


@Slf4j
@Component
public class TraceIdFilter implements Filter {
    private static final String TRACE_ID = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = UUID.randomUUID().toString();
        MDC.put(TRACE_ID, traceId);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }
}
