package top.liisla.electronicphotoalbum.Aspect;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.liisla.electronicphotoalbum.Dao.Aspect.AspectDao;
import top.liisla.electronicphotoalbum.Entity.Aspect.CheckUserLoginEntityAspect;
import top.liisla.electronicphotoalbum.Exception.UnauthorizedException.UnauthorizedException;

@Aspect
@Component
@Lazy
public class UserLoginAspect {

    private final AspectDao aspectDao;

    public UserLoginAspect(AspectDao aspectDao) {
        this.aspectDao = aspectDao;
    }

    @Before("execution(* top.liisla.electronicphotoalbum.Service..*.*(..)) && !execution(* top.liisla.electronicphotoalbum.Service.Join..*.*(..))")
    public void checkUserLogin() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        if (attributes != null) {
            request = attributes.getRequest();
            CheckUserLoginEntityAspect checkUserLoginEntityAspect;
            try {
                checkUserLoginEntityAspect = getCheckUserLoginEntityAspect(request);
            } catch (UnauthorizedException e) {
                throw new UnauthorizedException("401 No permission");
            }
            if (checkUserLoginEntityAspect.getUserID() != null && !aspectDao.checkUserLoginKey(checkUserLoginEntityAspect)) {
                throw new UnauthorizedException("401 No permission");
            }
        }
//        throw new UnauthorizedException("未登录或登录无效");
    }

    private static CheckUserLoginEntityAspect getCheckUserLoginEntityAspect(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        CheckUserLoginEntityAspect checkUserLoginEntityAspect = new CheckUserLoginEntityAspect();
        for (Cookie cookie : cookies) {
            switch (cookie.getName()) {
                case "userID":
                    checkUserLoginEntityAspect.setUserID(cookie.getValue());
                    break;
                case "userKey":
                    checkUserLoginEntityAspect.setUserKey(cookie.getValue());
                    break;
            }
        }
        return checkUserLoginEntityAspect;
    }
}
