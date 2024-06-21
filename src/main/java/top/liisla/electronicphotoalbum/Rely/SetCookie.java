package top.liisla.electronicphotoalbum.Rely;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class SetCookie {
    public Cookie createCookie(String name, String value, String domain, String path, int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        return cookie;
    }
}