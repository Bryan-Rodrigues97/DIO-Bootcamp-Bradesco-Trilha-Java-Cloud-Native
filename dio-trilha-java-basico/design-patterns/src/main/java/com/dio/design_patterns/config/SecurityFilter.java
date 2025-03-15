package com.dio.design_patterns.config;

import com.dio.design_patterns.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final UserService userService;

    public SecurityFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization != null && headerAuthorization.startsWith("Basic ")) {
            var basicToken = headerAuthorization.substring("Basic ".length());
            byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);

            String basicTokenValue = new String(basicTokenDecoded);
            String[] basicAuthsSplit = basicTokenValue.split(":", 2);

            if (userService.validateUser(basicAuthsSplit[0], basicAuthsSplit[1])){
                var authToken = new UsernamePasswordAuthenticationToken(basicAuthsSplit[0], null, null);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
