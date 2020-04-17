package club.xiaoandx.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SuppressWarnings("unchecked")
@Configuration
public class SwaggerConfig {
	@Value("${sys.version}")
	private String systemPublish;

	/**
	 * @Title: taskApi
	 * @Description  .paths(or(regex("/v1/open/studio/.*"))) //过滤的接口（添加过滤的访问接口）
	 * @Date 16:40 2019/11/7
	 * @version:V0.1
	 * @Author: zhouwei
	 **/
	@Bean
    public Docket taskApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("工作室管理相关模块相关API")
                .pathMapping("/")
                .select()
                .paths(or(regex("/v1/open/studio/.*")))
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("工作室管理相关模块相关API")
                        .version(systemPublish)
                        .build());
    }
}