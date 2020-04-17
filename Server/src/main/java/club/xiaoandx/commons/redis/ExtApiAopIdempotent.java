/**
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package club.xiaoandx.commons.redis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import club.xiaoandx.commons.core.Parameter;
import club.xiaoandx.commons.exception.NoAuthenticationException;
import club.xiaoandx.commons.utils.RedisTokenUtil;

/**  
 * <p> 
 *	token验证AOP，通过拦截请求获取请求（head，from）中的token参数并验证token，验证无误放开请求访问Controller对应方法，
 *	token异常（为空，请求中不包含token，token错误）时，提示请求者（{"code": 4008909,"massage": "Token invalid, please re-obtain the latest token"}）
 * </p>
 * @ClassName:ExtApiAopIdempotent   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-21 11:37
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@Aspect
@Component
public class ExtApiAopIdempotent {
	@Autowired
	private RedisTokenUtil redisTokenUtil;

	@Pointcut("execution(public * club.xiaoandx.studio.controller.*.*(..))")
	public void rlAop() {
	}

	/**
	 * @Title: before
	 * @Description 前置通知转发Token参数
	 * @Date 8:32 2019/11/19
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param [point]
	 **/
	@Before("rlAop()")
	public void before(JoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		ExtApiToken extApiToken = signature.getMethod().getDeclaredAnnotation(ExtApiToken.class);
		if (extApiToken != null) {
			extApiToken();
		}
	}

	/**
	 * @Title: doAround
	 * @Description 环绕通知验证token参数(是否为空，是否格式错误，请求中是否包含token)
	 * @Date 8:42 2019/11/19
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param [proceedingJoinPoint]
	 * @return java.lang.Object
	 **/
	@Around("rlAop()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		ExtApiIdempotent extApiIdempotent = signature.getMethod().getDeclaredAnnotation(ExtApiIdempotent.class);
		if (extApiIdempotent != null) {
			return extApiIdempotent(proceedingJoinPoint, signature);
		}
		// 放行
		Object proceed = proceedingJoinPoint.proceed();
		return proceed;
	}

	/**
	 * @Title: extApiIdempotent
	 * @Description 验证token的具体方法. 1、先判断请求类型 head 还是 from；2、从请求中获取token，有token放行，无token输出异常；3、获取token后将验证token是否为null，同理逻辑执行；
	 * 4、token不为空，将token与缓存中的token值对比，查询是否存在token，同理逻辑执行。
	 * @Date 8:43 2019/11/19
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param [proceedingJoinPoint, signature]
	 * @return java.lang.Object
	 **/
	public Object extApiIdempotent(ProceedingJoinPoint proceedingJoinPoint, MethodSignature signature)
			throws Throwable {
		ExtApiIdempotent extApiIdempotent = signature.getMethod().getDeclaredAnnotation(ExtApiIdempotent.class);
		if (extApiIdempotent == null) {
			// 直接执行程序
			Object proceed = proceedingJoinPoint.proceed();
			return proceed;
		}
		// 代码步骤：
		// 1.获取令牌 存放在请求头中
		HttpServletRequest request = getRequest();
		String valueType = extApiIdempotent.value();
		if (StringUtils.isEmpty(valueType)) {
			throw new NoAuthenticationException();
		}
		String token = null;
		if (valueType.equals(Parameter.EXTAPIHEAD)) {
			token = request.getHeader("token");
		} else {
			token = request.getParameter("token");
		}
		if (StringUtils.isEmpty(token)) {
			throw new NoAuthenticationException();
		}
		if (!redisTokenUtil.findToken(token)) {
			throw new NoAuthenticationException();
		}
		Object proceed = proceedingJoinPoint.proceed();
		return proceed;
	}

	public void extApiToken() {
		String token = redisTokenUtil.getToken();
		getRequest().setAttribute("token", token);

	}

	public HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request;
	}

	public void response(String msg) throws IOException {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		try {
			writer.println(msg);
		} catch (Exception e) {

		} finally {
			writer.close();
		}
	}
}
