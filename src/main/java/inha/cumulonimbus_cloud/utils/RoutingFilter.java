package inha.cumulonimbus_cloud.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RoutingFilter implements Filter {

    private static final List<String> staticResources = Arrays.asList(
            "/css", "/js", "/img", "/fonts", "/favicon.ico", "/manifest.json", "/static/", "/swagger-ui", "/v3"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // 정적 리소스나 스웨거 UI 경로를 제외한 경로에 대해 index.html로 포워딩
        if (staticResources.stream().noneMatch(path::startsWith)) {
            if (!path.startsWith("/api/v1")) {
                req.getRequestDispatcher("/index.html").forward(req, res);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
