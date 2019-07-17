package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date startTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    @Before("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        //访问时间
        startTime = new Date();

        //访问的类
        executionClass = jp.getTarget().getClass();

        //访问的方法名
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs();

        Class[] classes = new Class[args.length];

        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }

        executionMethod = executionClass.getMethod(methodName, classes);


    }

    @After("execution(* cn.itcast.ssm.controller.*.*(..))")
    public void doAfter() {

        if (executionClass != SysLog.class) {

            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);

            if (classAnnotation != null) {

                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);


                if (methodAnnotation != null) {

                    SysLog sysLog = new SysLog();
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
                    sysLog.setUrl(classAnnotation.value()[0] + methodAnnotation.value()[0]);

                    //访问时长
                    sysLog.setExecutionTime(new Date().getTime() - startTime.getTime());

                    //访问方法
                    sysLog.setMethod(executionMethod.getName());

                    System.out.println(sysLog);

                    sysLogService.save(sysLog);
                }

            }
        }
    }
}
