package com.coolors.coolors.security.utils;

import jakarta.servlet.http.*;
import org.springframework.security.core.userdetails.UserDetails;

public class CookieUtil {
    public Cookie gerarCookie(UserDetails userDetails) {
        String token = new JWTUtil().gerarToken(userDetails);
        Cookie cookie = new Cookie("Coolors", token);
        cookie.setPath("/");
        cookie.setMaxAge(10000);
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Coolors")) {
                    return cookie;
                }
            }
        }
        throw new Exception("Cookie com o nome Coolors não encontrado");
    }
}
