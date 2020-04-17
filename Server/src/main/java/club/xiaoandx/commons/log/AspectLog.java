/**
 * 
 */
package club.xiaoandx.commons.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 统一日志记录配置，目前只记录的web端入口地方的日志
 * 日志拦截器
 * @author zhen.pan
 *
 */
@Aspect
@Component
@Slf4j
public class AspectLog {

	@Pointcut("execution(public * club.xiaoandx..controller..*Controller.*(..))")
    public void aspectLog(){}


    @Around("aspectLog()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long starttime = System.currentTimeMillis();
            Object result = joinPoint.proceed();

            if(log.isDebugEnabled()) {
            	Object[] args = joinPoint.getArgs();
                log.debug("{} {} Params[{}] Response[{}] speed time:[{}]ms", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(args), result, (System.currentTimeMillis() - starttime));
            }
            return result;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @AfterThrowing(pointcut = "aspectLog()",throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.warn("{} {} Params[{}] ,throws: [{}]", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), error.getMessage());
    }
}