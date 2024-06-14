package com.coolors.coolors.security.filter;

import com.coolors.coolors.security.service.AuthenticationService;
import com.coolors.coolors.security.utils.CookieUtil;
import com.coolors.coolors.security.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class FilterAuthentication extends OncePerRequestFilter {

    private SecurityContextRepository securityContextRepository;
    private final CookieUtil cookieUtil = new CookieUtil();
    private final JWTUtil jwtUtil = new JWTUtil();

    private AuthenticationService autenticacaoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!rotaPublica(request)) {
            Cookie cookie;
            try {
                cookie = cookieUtil.getCookie(request);
            } catch (Exception e) {
                response.sendError(401);
                return;
            }
            String token = cookie.getValue();
            String username = jwtUtil.getNome(token);
            UserDetails userDetails = autenticacaoService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextRepository.saveContext(context, request, response);
            Cookie newCookie = cookieUtil.gerarCookie(userDetails);
            response.addCookie(newCookie);
        }
        filterChain.doFilter(request, response);
    }

    private boolean rotaPublica(HttpServletRequest request) {

        return request.getMethod().equals("POST") && request.getRequestURI().equals("/login") || request.getRequestURI().equals("/usuario/create");
    }
}
