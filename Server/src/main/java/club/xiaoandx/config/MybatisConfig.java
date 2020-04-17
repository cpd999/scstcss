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
package club.xiaoandx.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * <p> 
 * Mybatis配置类
 * </p> 
 * @ClassName:MybatisConfig   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-14 10:23
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@Configuration
@MapperScan(basePackages = "club.xiaoandx.*.mapper" , sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfig {
	
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Autowired HikariDataSource hikariDataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(hikariDataSource);
		Resource[] resource = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml");
		sqlSessionFactoryBean.setMapperLocations(resource);
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager test1TransactionManager(@Autowired HikariDataSource hikariDataSource) {
		return new DataSourceTransactionManager(hikariDataSource);
	}
	
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate test1SqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
