package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.service.ISysLogService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date startTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法
    private String url;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    @Before("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doBefore(){
        //访问时间
        startTime=new Date();

        url = request.getRequestURI();
    }

    @After("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doAfter(){

        SysLog sysLog=new SysLog();
        //访问时间
        sysLog.setVisitTime(startTime);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = user.getUsername();
        //访问用户名称
        sysLog.setUsername(username);

        String ip = request.getRemoteAddr();
        //访问ip
        sysLog.setIp(ip);

        //访问路径
        sysLog.setUrl(url);

        //访问时长
        sysLog.setExecutionTime(new Date().getTime()-startTime.getTime());


        sysLogService.save(sysLog);
    }

}
