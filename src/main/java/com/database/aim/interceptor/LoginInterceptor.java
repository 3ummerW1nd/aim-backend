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
            System.out.println("token是null，没有从请求得到");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, token, id");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.sendError(401);
            return false;
        }
        String tokenId = httpServletRequest.getHeader("id");
        if(!redisUtil.exists(tokenId)){
            System.out.println("token在redis不存在");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, token, id");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.sendError(401);
            return false;
        }
        String redisToken = redisUtil.get(tokenId);
        if(redisUtil.ttl(redisToken) <= System.currentTimeMillis()){
            System.out.println("token超时");
            redisUtil.del(tokenId);
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, token, id");
            httpServletResponse.sendError(401);
            return false;
        }
        if(!redisToken.equals(token)) {
            System.out.println("token错误");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, token, id");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpServletResponse.sendError(401);
            return false;
        }
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie, token, id");
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