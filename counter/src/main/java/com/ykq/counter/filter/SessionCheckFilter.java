package com.ykq.counter.filter;

import com.google.common.collect.Sets;
import com.ykq.counter.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SessionCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Autowired
    private AccountService accountService;

    private Set<String> whiteRootPaths = Sets.newHashSet(
            "login","msgsocket","test","api"
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //解决ajax跨域问题
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("Access-Control-Allow-Origin","*");

        //filterChain.doFilter(servletRequest, servletResponse);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //http://localhost:8090/login/pwdsetting
        //  /login/pwdsetting
        String path = request.getRequestURI();
        String[] split = path.split("/");
//        System.out.println(path);
//        System.out.println(split.length);
        if(split.length < 2){   //非法url
            request.getRequestDispatcher(
                    "/login/loginfail"
            ).forward(servletRequest, servletResponse);
        }else {
            if(!whiteRootPaths.contains(split[1])){
                //不在白名单 验证token
                if(accountService.accountExistInCache(
                        request.getParameter("token")
                )){
                    filterChain.doFilter(servletRequest, servletResponse);
                }else {
                    request.getRequestDispatcher(
                            "/login/loginfail"
                    ).forward(servletRequest, servletResponse);
                }
            }else {
                //在白名单 放行
                filterChain.doFilter(servletRequest, servletResponse);
            }



        }



    }

    @Override
    public void destroy() {

    }
}
