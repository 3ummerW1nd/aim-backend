package com.database.aim.interceptor;

import com.database.aim.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor  implements HandlerInterceptor{

    RedisUtil redisUtil = new RedisUtil();

    @Override
    public boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        String contextPath=session.getServletContext().getContextPath();
        String[] notRequireAuthPages = new String[]{
                "login",
        };//把不需要拦截的请求写在这
        String uri = httpServletRequest.getRequestURI();
        String page = StringUtils.remove(uri, contextPath+"/");
        if(beginWith(page, notRequireAuthPages)) {
            return true;
        }
        String token = httpServletRequest.getHeader("token");
        if (null == token){
            httpServletResponse.sendRedirect("/swagger-ui.html");//设置重定向页面
            return false;
        }
        String tokenId = redisUtil.getKey(token);
        if(!redisUtil.exists(tokenId)){
            httpServletResponse.sendRedirect("/swagger-ui.html");//设置重定向页面
            return false;
        }
        String redisToken = redisUtil.get(tokenId);
        if(redisUtil.ttl(redisToken) <= System.currentTimeMillis()){
            redisUtil.del(tokenId);
            httpServletResponse.sendRedirect("/swagger-ui.html");//设置重定向页面
            return false;
        }
        if(!redisToken.equals(token)) {
            httpServletResponse.sendRedirect("/swagger-ui.html");//设置重定向页面
            return false;
        }
        return true;
    }

    private boolean beginWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}