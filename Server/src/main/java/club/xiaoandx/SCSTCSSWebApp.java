/*
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
package club.xiaoandx;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**  
 * <p>四川科技职业学院学生工作室官网(后端项目启动类)</p> 
 * @ClassName:SCSTCSSWebApp   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-12 13:39
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
public class SCSTCSSWebApp {
	//通过启动内置的web容器启动项目
	public static void main(String[] args) {
		SpringApplication.run(SCSTCSSWebApp.class, args);
	}

    /**
     * @Title: servletContainer
     * @Description 设置http协议转https协议(本设置以 80(http)  443(https) 为例)
     * @Date 20:56 2019/11/29
     * @version:V0.1
     * @Author: zhouwei
     * @Param []
     * @return org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     **/
	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
				constraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}

	/**
	 * @Title: httpConnector
	 * @Description 设置端口跳转
	 * @Date 20:56 2019/11/29
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param []
	 * @return org.apache.catalina.connector.Connector
	 **/
	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		//Connector监听的http的端口号
		connector.setPort(80);
		connector.setSecure(false);
		//监听到http的端口号后转向到的https的端口号
		connector.setRedirectPort(443);
		return connector;
	}
}
