package top.liisla.electronicphotoalbum.Rely;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class GetCookieValue {
    public HashMap<String, String> getCookieValueByCookieName(HttpServletRequest request, @org.jetbrains.annotations.NotNull List<String> cookieNames) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) return null;
        HashMap<String, String> cookieNameMap = new HashMap<>();
        cookieNames.forEach(cookieName -> {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookieNameMap.put(cookie.getName(), cookie.getValue());
                }
            }
        });
        return cookieNameMap;
    }
}
