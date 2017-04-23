package com.tianl.dianying.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/12/22.
 */
public class LoginInterceptor implements HandlerInterceptor{

    private final static Logger logger = LoggerFactory
            .getLogger(LoginInterceptor.class);

    @Value("#{configProperties['session.user.name']}")
    protected String sessionUser;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final Object member = request.getSession().getAttribute(sessionUser);
        if (member == null) {
            logger.warn("{}不存在session中, 取得:{}.", sessionUser, member);
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
