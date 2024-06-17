package inha.cumulonimbus_cloud.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class RoutingFilter implements Filter {
    private static final List<String> staticResources = Arrays.asList(
            "/css", "/js", "/img", "/fonts", "/favicon.ico", "/manifest.json", "/static/"
    );
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        log.info("Request URI: {}", req.getRequestURI());
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if(staticResources.stream().noneMatch(path::startsWith)){
            if(!path.startsWith("/api/v1") && !path.startsWith("/swagger-ui") && !path.startsWith("/v3")) {
                req.getRequestDispatcher("/index.html").forward(req, res);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
