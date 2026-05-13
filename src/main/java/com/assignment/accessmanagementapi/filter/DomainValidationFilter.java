package com.assignment.accessmanagementapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class DomainValidationFilter
        extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String host =
                request.getHeader("Host");

        String path =
                request.getRequestURI();

        if (path.startsWith("/admin")) {

            if (host == null
                    || !host.contains("admin.myapp.com")) {

                response.setStatus(
                        HttpServletResponse.SC_FORBIDDEN
                );

                response.getWriter().write(
                        "Admin access allowed only from admin.myapp.com"
                );

                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}